package com.example.mrose.clickpark;

import java.util.ArrayList;
import java.util.List;

public class ListaPlazas {
    private List<Plaza> plazas;

    public ListaPlazas(List<Plaza> plazas) {
        this.plazas = new ArrayList<>();
    }

    public List<Plaza> getPlazas() {
        return plazas;
    }

    public void setPlazas(List<Plaza> plazas) {
        this.plazas = plazas;
    }
}
