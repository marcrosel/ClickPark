package com.example.mrose.clickpark;

public class ListaPlazas {
    private int nPlazas;
    private Plaza[] listaPlazas;

    public ListaPlazas(int nPlazas){
        this.nPlazas= nPlazas;
        listaPlazas= new Plaza[nPlazas];
    }

}
