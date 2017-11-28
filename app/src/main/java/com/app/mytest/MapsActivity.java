package com.app.mytest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Activity getActivity;

    private GoogleMap mMap;

    ArrayList<PojoClass> getPojoList = new ArrayList<>();

    private URL url;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        getActivity = this;

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        getPojoList= (ArrayList<PojoClass>)bundle.getSerializable("value");



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        for (int i = 0; i < getPojoList.size(); i++) {

            try {
                url = new URL(getPojoList.get(i).getImage());
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            initMap(i, getPojoList.get(i).getLat(), getPojoList.get(i).getLng(), bitmap);
        }

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

    private void initMap(int i, double latitude, double longitude, Bitmap bitmap1) {
        // Log.e("GoogleMapDemo", "Lat:" + mapLatitude + "Long:" + mapLongtitude);
        if (latitude != 0 && longitude != 0) {
            //mMap.clear();
            LatLng latlng = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.fromBitmap(bitmap1)).title(getPojoList.get(i).getName()).snippet(""));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16.0f));

            CameraPosition camPos = new CameraPosition.Builder().target(latlng).zoom(13).build();
            CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
            mMap.animateCamera(camUpd3);
        }
    }
}
