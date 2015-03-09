package br.com.mobi.viajabessa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import br.com.mobi.viajabessa.R;
import br.com.mobi.viajabessa.adapters.PackageItemAdapter;
import br.com.mobi.viajabessa.itens.JsonLocation;

public class PackageListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    ListView packageListView;
    PackageItemAdapter packageItemAdapter;
    List<JsonLocation> travelPackageList = new ArrayList<>();
    Bundle mBundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBundle = getIntent().getExtras();

        // populating views
        instanceViews();
    }

    private void instanceViews() {

        // instance listview
        packageListView = (ListView) findViewById(R.id.packageListView);

        if(mBundle != null) {

            // create and add jsonObjects in list
            Gson gson = new Gson();
            List<JsonLocation> locationsFromServer = gson.fromJson(mBundle.getString("jsonResponse"), new TypeToken<List<JsonLocation>>(){}.getType());

            for (JsonLocation location : locationsFromServer)
                travelPackageList.add(location);

            // populating adapter
            packageItemAdapter = new PackageItemAdapter(travelPackageList, this);

            // set adapter to listview
            packageListView.setAdapter(packageItemAdapter);

            packageListView.setOnItemClickListener(this);
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // pass the selected package to a generic activity to show informations about it
        Intent intentToDetails = new Intent(this, TravelDetailsActivity.class);
        intentToDetails.putExtra("type", packageItemAdapter.getItem(position).getType());
        intentToDetails.putExtra("name", packageItemAdapter.getItem(position).getTitle());
        intentToDetails.putExtra("value", packageItemAdapter.getItem(position).getValue());
        intentToDetails.putExtra("description", packageItemAdapter.getItem(position).getDescription());
        intentToDetails.putExtra("picture", packageItemAdapter.getItem(position).getImageUrl());
        intentToDetails.putExtra("latitude", packageItemAdapter.getItem(position).getLatitude());
        intentToDetails.putExtra("longitude", packageItemAdapter.getItem(position).getLongitude());
        startActivity(intentToDetails);
    }
}
