package com.fonarev.convoy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
//    private LocationManager mLocationManager;
    private double longitude, latitude;
//    LatLng myLocation = new LatLng(0,0);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ff757575"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        askForPermissions();
        checkLocation();
//        toggleBestUpdates();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }
    private boolean isLocationEnabled() {
        return mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

//        public void toggleBestUpdates() {
//        if(!checkLocation())
//            return;
//        try {
//                Criteria criteria = new Criteria();
//                criteria.setAccuracy(Criteria.ACCURACY_FINE);
//                criteria.setAltitudeRequired(false);
//                criteria.setBearingRequired(false);
//                criteria.setCostAllowed(true);
//                criteria.setPowerRequirement(Criteria.POWER_LOW);
//                String provider = mLocationManager.getBestProvider(criteria, true);
//                if(provider != null) {
//                    mLocationManager.requestLocationUpdates(provider, 2 * 60 * 1000, 10, locationListenerBest);
//            }
//        } catch (SecurityException ex) {
//            Log.d("mapActivity", "error with security exceptions");
//        }
//    }

//    private final LocationListener locationListenerBest = new LocationListener() {
//        public void onLocationChanged(final Location location) {
//            longitude = location.getLongitude();
//            latitude = location.getLatitude();
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    longitude = location.getLongitude();
//                    latitude = location.getLatitude();
//                }
//            });
//        }
//
//        @Override
//        public void onStatusChanged(String s, int i, Bundle bundle) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String s) {
//
//        }
//
//        @Override
//        public void onProviderDisabled(String s) {
//
//        }
//    };
    protected void askForPermissions (){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
//            googleMap.animateCamera(CameraUpdateFactory.zoomTo(20));
//            mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
            mMap.setMyLocationEnabled(true);

        } catch (SecurityException ex) {
            Log.d("on map ready", "security issues");
        }
    }
}





