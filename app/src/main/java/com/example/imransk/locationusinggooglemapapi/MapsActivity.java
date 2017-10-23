package com.example.imransk.locationusinggooglemapapi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

//check the metwork provider is enabled
        if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)) {

            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
//get the latitude
                    double latitude = location.getLatitude();
//get the longitude
                    double longitude = location.getLongitude();
//Instantiant the LatLng , Class
                    LatLng latLngObj = new LatLng(latitude, longitude);

//Instantiant the Geocoder , Class
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality() + ", ";
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLngObj).title(str));
//নিচের লাইনে মানে হলো আমারা যখন গুগল ম্যাপ ওপেন করবো তখন আমারা যেখানে আছি সেই জায়গা মবাইলে দেখাবে
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//নিচের লাইনে মানে হলো আমারা যখন গুগল ম্যাপ ওপেন করবো তখন  আমাদের প্লেস টা জুম করে দেখাবে
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngObj, 10.2f));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        } else if (locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
//get the latitude
                    double latitude = location.getLatitude();
//get the longitude
                    double longitude = location.getLongitude();
//Instantiant the LatLng , Class
                    LatLng latLngObj = new LatLng(latitude, longitude);

//Instantiant the Geocoder , Class
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality() + ", ";
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLngObj).title(str));
//নিচের লাইনে মানে হলো আমারা যখন গুগল ম্যাপ ওপেন করবো তখন আমারা যেখানে আছি সেই জায়গা মবাইলে দেখাবে
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//নিচের লাইনে মানে হলো আমারা যখন গুগল ম্যাপ ওপেন করবো তখন  আমাদের প্লেস টা জুম করে দেখাবে
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngObj,10.2f));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }

    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
////নিচের লাইনে মানে হলো আমারা যখন গুগল ম্যাপ ওপেন করবো তখন আমারা যেখানে আছি সেই জায়গা মবাইলে দেখাবে
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
////নিচের লাইনে মানে হলো আমারা যখন গুগল ম্যাপ ওপেন করবো তখন  আমাদের প্লেস টা জুম করে দেখাবে
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10.2f));
    }
}
