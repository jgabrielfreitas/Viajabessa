package br.com.mobi.viajabessa.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.mobi.viajabessa.R;
import br.com.mobi.viajabessa.itens.JsonLocation;

/**
 * Created by JGabrielFreitas on 24/02/2015 - 19:25.
 */
public class PackageItemAdapter extends BaseAdapter {

    List<JsonLocation> travelPackageList;
    Activity activity;

    public static final int AIRPLANE = 1;
    public static final int SHIP     = 2;

    public PackageItemAdapter(List<JsonLocation> travelPackageList, Activity activity) {
        this.travelPackageList = travelPackageList;
        this.activity = activity;
    }

    public int getCount() {
        return travelPackageList.size();
    }

    public JsonLocation getItem(int position) {
        return travelPackageList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        JsonLocation item = this.travelPackageList.get(position);

        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.package_row_item_adapter, parent, false);

        ImageView image = (ImageView) view.findViewById(R.id.packageDestinationImageView);
        TextView  packageName = (TextView) view.findViewById(R.id.packageNameTextView);
        TextView  value = (TextView) view.findViewById(R.id.packageValueTextView);
        ImageView typePackageImageView = (ImageView) view.findViewById(R.id.typePackageImageView);

        if (item.getType() == AIRPLANE)
            typePackageImageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.airplane));
        else if(item.getType() == SHIP)
            typePackageImageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.ship));

        Picasso.with(activity).load(item.getImageUrl()).into(image);
        packageName.setText(item.getTitle());
        value.setText(item.getValue());

        value.setTypeface(null, Typeface.BOLD);

        return view;
    }
}
