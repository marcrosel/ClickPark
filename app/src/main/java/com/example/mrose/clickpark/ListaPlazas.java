package com.example.mrose.clickpark;

import java.util.ArrayList;
import java.util.List;

public class ListaPlazas {
    private List<Plaza> slots;

    public ListaPlazas() {
        this.slots = new ArrayList<>();
    }

    public List<Plaza> getPlazas() {
        return slots;
    }

    public void setPlazas(List<Plaza> plazas) {
        this.slots = plazas;
    }
}
