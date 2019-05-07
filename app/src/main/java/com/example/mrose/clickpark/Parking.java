package com.example.mrose.clickpark;

public class Parking {
    //atributos
    int id, company_number;
    ListaPlazas listaPlazas;
    Localizacion loc;
    String name;

    public Parking(int id, int company_number, ListaPlazas listaPlazas, Localizacion loc, String name) {
        this.id = id;
        this.company_number = company_number;
        this.listaPlazas = listaPlazas;
        this.loc = loc;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_number() {
        return company_number;
    }

    public void setCompany_number(int company_number) {
        this.company_number = company_number;
    }

    public ListaPlazas getListaPlazas() {
        return listaPlazas;
    }

    public void setListaPlazas(ListaPlazas listaPlazas) {
        this.listaPlazas = listaPlazas;
    }

    public Localizacion getLoc() {
        return loc;
    }

    public void setLoc(Localizacion loc) {
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
