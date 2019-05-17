package com.example.mrose.clickpark;

import java.util.ArrayList;
import java.util.List;

public class ListaPlantas {
    private List<Planta> floors;

    public ListaPlantas() {
        this.floors = new ArrayList<>();
    }

    public List<Planta> getPlantas() {
        return floors;
    }

    public void setPlantas(List<Planta> plantas) {
        this.floors = plantas;
    }
}