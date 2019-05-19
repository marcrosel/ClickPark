package com.example.mrose.clickpark;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;


public class MapsActivitySes extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
    private Marker marcador, marcadorParking;
    double lat, lng;
    private static final String TAG = cat.tomasgis.module.communication.commapptesting.MainActivity.class.getSimpleName();
    ListaLocations locations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_bell);
        //CommManager.initializeQueu(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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
        miUbi();
        queryBaseData();
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

    private void agregarMarcadorParking(double lat, double lng, String adress) {
        LatLng coordenadas = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_parking_opt)).position(coordenadas).title("Parking: "+adress));
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

    protected void queryBaseData(){
        ContentResolver contentResolver = this.getContentResolver();

        String defaultOrder = ModelContracts.LocationModel.DEFAULT_SORT;
        String projections[] = ModelContracts.LocationModel.DEFAULT_PROJECTIONS;

        Cursor cursor= contentResolver.query(ModelContracts.LocationModel.buildContentUri(), projections, null, null, defaultOrder);
        int numLocalizaciones = cursor.getCount();

        cursor.moveToFirst();
        for(int i=0; i<numLocalizaciones;i++){
            Double latitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex(ModelContracts.LocationModel.LATITUDE)));
            Double longitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex(ModelContracts.LocationModel.LONGITUDE)));
            String name = cursor.getString(cursor.getColumnIndex(ModelContracts.LocationModel.STREET_ADDRESS));
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ModelContracts.LocationModel.ID)));

            if (id == 1)
                agregarMarcadorParking(latitude,longitude, name);

            cursor.moveToNext();
        }
    }



}
