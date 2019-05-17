package com.example.mrose.clickpark;

import java.util.ArrayList;
import java.util.List;

public class Planta {
    //atributos
    protected int id, company_number;
    protected String name;
    protected List<Plaza> slots;


    //constructor
    public Planta(int id,List<Plaza> slots, int company_number, String name){
        this.id=id;
        this.company_number=company_number;
        this.name=name;
        this.slots=slots;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Plaza> getSlots() {
        return slots;
    }

    public void setSlots(List<Plaza> slots) {
        this.slots = slots;
    }
}
