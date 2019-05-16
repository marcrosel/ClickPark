package com.example.mrose.clickpark;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class ListaLocations {
    private List<Localizacion>locations;

    public ListaLocations() {
        this.locations = new ArrayList<>();
    }

    public List<Localizacion> getLocations() {
        return locations;
    }

    public void setLocations(List<Localizacion> locations) {
        this.locations = locations;
    }
}
