package com.example.mrose.clickpark;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class Map extends Fragment implements OnMapReadyCallback {

    GoogleMap map;

    public Map() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        return v;
    }

    //Una vez creamos la view, ejecutaremos el metodo de debajo para adjuntar el maps
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1); //Vinculamos el mapa al fragment
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
        LatLng tgn= new LatLng(41.12, 1.25);
        MarkerOptions m1 = new MarkerOptions();
        m1.position(tgn).title("Tarragona");
        map.addMarker(m1);
        map.moveCamera(CameraUpdateFactory.newLatLng(tgn));

    }
}
