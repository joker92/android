package com.example.svilupposw.ToEat;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_location);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng TorinoCentro = new LatLng(45.074409,7.6791272);
        float zoomLevel = 12;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TorinoCentro, zoomLevel));
        mMap.addMarker(new MarkerOptions()
                .position(TorinoCentro)
                .title("Torino"));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.0776414 , 7.6646496))
                .title("via cibrario"));
        final Geocoder geocoder = new Geocoder(this, Locale.ITALIAN);

        MyApplication.getMyFirebaseRef().child("local").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
                Log.i("onChildAdded", dataSnapshot.getKey());

                final Local local = dataSnapshot.getValue(Local.class);
                String location = local.getAddress();

                try
                {
                    List<Address> addresses = geocoder.getFromLocationName(location, 1) ;
                    Address address = addresses.get(0);
                    double longitude = address.getLongitude();
                    double latitude = address.getLatitude();
                    LatLng localPosition = new LatLng(latitude,longitude);
                    mMap.addMarker(new MarkerOptions().position(localPosition).title(local.getName()).snippet(local.getMoney()+"€"));

                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.i("onChildChanged", dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i("onChildRemoved", dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.i("onChildMoved", "dataSnapshot.getKey()");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.i("onCancelled", firebaseError.getMessage());

            }
        });


    }
}
