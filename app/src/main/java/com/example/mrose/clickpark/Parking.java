package com.example.mrose.clickpark;

import java.util.List;

public class Parking {
    //atributos
    int id, company_number;
    protected List<Planta> floors;
    String name;

    public Parking(int id, int company_number, List<Planta> floors, String name) {
        this.id = id;
        this.company_number = company_number;
        this.floors = floors;
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

    public List<Planta> getFloors() {
        return floors;
    }

    public void setFloors(List<Planta> floors) {
        this.floors = floors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
