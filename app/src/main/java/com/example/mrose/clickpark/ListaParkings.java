package com.example.mrose.clickpark;

import java.util.ArrayList;
import java.util.List;

public class ListaParkings {
    private List<Parking> parkings;

    public ListaParkings(){ this.parkings = new ArrayList<>(); }

    public List<Parking> getParkings() {
        return parkings;
    }

    public void setParkings(List<Parking> parkings) {
        this.parkings = parkings;
    }
}
