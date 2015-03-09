package br.com.mobi.viajabessa.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.mobi.viajabessa.R;

public class PackageInMapActivity extends FragmentActivity {

    /**
     *  !!! WARNING !!!
     *
     *  If you test this activity, your SHA1 fingerprint isn't in my Google Developer Console application.
     *  So, you'll see nothing.
     *
     *  To see the map, install the apk in GitHub or give me your SHA1 using the follow command on terminal:
     *
     *      keytool -list -v -keystore [YOUR STORE HERE].keystore
     *
     *  Ex:
     *  CD:51:36:76:3B:BD:.......
     *
     * Happy debug.
     *
     * */

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    Bundle mBundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_in_map);

        setUpMapIfNeeded();
    }

    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {

        mBundle = getIntent().getExtras();

        String locationName = mBundle.getString("name");
        float latitude  = mBundle.getFloat("latitude");
        float longitude = mBundle.getFloat("longitude");
        LatLng mLatLng = new LatLng(latitude,longitude);

        // mark location in google map
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(mLatLng).title(locationName);
        Marker locationMarker = mMap.addMarker(markerOptions);
        locationMarker.showInfoWindow();

        // zoom in locationMarker
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 15));

    }
}
