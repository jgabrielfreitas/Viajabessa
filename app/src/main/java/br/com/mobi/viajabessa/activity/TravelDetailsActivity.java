package br.com.mobi.viajabessa.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.mobi.viajabessa.R;
import br.com.mobi.viajabessa.adapters.PackageItemAdapter;

public class TravelDetailsActivity extends ActionBarActivity implements View.OnClickListener{

    Bundle    mBundle;
    ImageView imageDetailsImageView;
    ImageView typeTravelImageView;
    TextView  packageNameTextView;
    TextView  descriptionTitleTextView;
    TextView  descriptionTextView;
    TextView  packageValueTextView;
    Button    buyPackageButton;
    Button    mapButton;

    String latitude;
    String longitude;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_details);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get informations from item selected in listview
        mBundle = getIntent().getExtras();

        instanceViews();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void instanceViews() {

        // instance all views
        buyPackageButton = (Button) findViewById(R.id.buyPackage);
        mapButton        = (Button) findViewById(R.id.seeMapButton);
        imageDetailsImageView = (ImageView) findViewById(R.id.imageDetailsImageView);
        typeTravelImageView   = (ImageView) findViewById(R.id.typeTravelImageView);
        packageNameTextView   = (TextView)  findViewById(R.id.packageNameTextView);
        descriptionTextView   = (TextView)  findViewById(R.id.descriptionTextView);
        packageValueTextView  = (TextView)  findViewById(R.id.packageValueTextView);
        descriptionTitleTextView = (TextView) findViewById(R.id.descriptionTitleTextView);

        // just to put this views with some TypeFaces
        descriptionTitleTextView.setTypeface(null, Typeface.ITALIC);
        packageValueTextView.setTypeface(null, Typeface.BOLD);
        buyPackageButton.setTypeface(null, Typeface.BOLD);
        mapButton.setTypeface(null, Typeface.BOLD);

        buyPackageButton.setOnClickListener(this);
        mapButton.setOnClickListener(this);

        putInformationsInViews();
    }

    private void putInformationsInViews() {

        // bundle validation
        if (mBundle != null) {

            Picasso.with(this).load(mBundle.getString("picture")).into(imageDetailsImageView);
            packageNameTextView.setText(mBundle.getString("name"));
            descriptionTextView.setText(mBundle.getString("description"));
            packageValueTextView.setText(mBundle.getString("value"));

            if(mBundle.getInt("type") == PackageItemAdapter.AIRPLANE)
                typeTravelImageView.setImageDrawable(getResources().getDrawable(R.drawable.airplane));
            else if(mBundle.getInt("type") == PackageItemAdapter.SHIP)
                typeTravelImageView.setImageDrawable(getResources().getDrawable(R.drawable.ship));

            latitude  = mBundle.getString("latitude");
            longitude = mBundle.getString("longitude");
        }

    }

    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {

            case R.id.buyPackage:
                // simulate purchase
                intent = new Intent(this, PurchaseConfirmActivity.class);
                intent.putExtra("value", packageValueTextView.getText().toString());
                finish();
                break;

            case R.id.seeMapButton:
                // open location in google map
                intent = new Intent(this, PackageInMapActivity.class);
                intent.putExtra("name", mBundle.getString("name"));
                intent.putExtra("latitude", Float.parseFloat(latitude));
                intent.putExtra("longitude", Float.parseFloat(longitude));
                break;
        }

        startActivity(intent);

    }
}
