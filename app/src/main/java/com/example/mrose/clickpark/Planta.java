package com.example.mrose.clickpark;

public class Planta {
    //atributos
    protected int id, company_number;
    protected String name;
    protected ListaPlazas listaPlazas;


    //constructor
    public Planta(int id,ListaPlazas listaPlazas, int company_number, String name){
        this.id=id;
        this.company_number=company_number;
        this.name=name;
        this.listaPlazas=listaPlazas;

    }

    //GETTERS I SETTERS
    public int getId(){
        return id;
    }

    public int getCompany_number(){
        return company_number;
    }

    public String getName(){
        return name;
    }
}
