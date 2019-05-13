package com.example.mrose.clickpark;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class ListaLocations {
    private List<Location>locations;

    public ListaLocations() {
        this.locations = new ArrayList<>();
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
