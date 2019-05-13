package com.example.mrose.clickpark;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.GsonBuilder;

import java.util.List;

import cat.tomasgis.module.communication.CommManager;
import cat.tomasgis.module.communication.base.AppURL;
import cat.tomasgis.module.communication.listeners.IDataReceiver;
import cat.tomasgis.module.communication.listeners.StringResponseListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, IDataReceiver {

    private GoogleMap mMap;
    private Marker marcador, marcadorParking;
    double lat, lng;
    private static final String TAG = cat.tomasgis.module.communication.commapptesting.MainActivity.class.getSimpleName();
    StringResponseListener stringListener = new StringResponseListener(this);
    ListaLocations locations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        CommManager.initializeQueu(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (! CommManager.callRequest(AppURL.LOCATIOM_URL,stringListener))
            Toast.makeText(this, "Call error", Toast.LENGTH_SHORT).show();
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
        miUbi();
    }


    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbi = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) {
            marcador.remove();
        }
        marcador= mMap.addMarker(new MarkerOptions().position(coordenadas).title("Posición actual"));
        mMap.animateCamera(miUbi);
    }

    private void agregarMarcadorParking(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        marcadorParking= mMap.addMarker(new MarkerOptions().position(coordenadas).title("Parking"));
    }

    private void actualizarUbi(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbi(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    private void miUbi() {
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            return;
        }
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbi(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,locationListener);
    }

    @Override
    public void onReceiveData(String s) {
        if (s !=null) {
            if (s.length() > 0) {
                Toast.makeText(this, "Data received", Toast.LENGTH_SHORT).show();
                Log.d(TAG,s);
            }
        }
        else
        {
            Toast.makeText(this, "Data NOT received", Toast.LENGTH_SHORT).show();
            Log.e(TAG,"No data to show");
        }

        GsonBuilder gson = new GsonBuilder();
        String parse= "{\"locations\":"+s+"}";
        locations=gson.create().fromJson(parse,ListaLocations.class);


        for ( Location location:locations.getLocations()){
            agregarMarcadorParking(location.getLatitude(),location.getLongitude());
        }
    }

}
