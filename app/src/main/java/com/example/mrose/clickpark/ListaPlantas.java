package com.example.mrose.clickpark;

import java.util.ArrayList;
import java.util.List;

public class ListaPlantas {
    private List<Planta> plantas;

    public ListaPlantas(List<Planta> plantas) {
        this.plantas = new ArrayList<>();
    }

    public List<Planta> getPlantas() {
        return plantas;
    }

    public void setPlantas(List<Planta> plantas) {
        this.plantas = plantas;
    }
}