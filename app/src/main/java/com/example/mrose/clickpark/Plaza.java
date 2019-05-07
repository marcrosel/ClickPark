package com.example.mrose.clickpark;

public class Plaza {
    //atributos
    protected int id, company_number;
    protected String sloot_type, sloot_color, sloot_state, name, state_change_date, floor_id;

    public Plaza(int id, String sloot_type, String sloot_color, String sloot_state, int company_number, String name, String state_change_date, String floor_id){
        this.id =id;
        this.sloot_type=sloot_type;
        this.sloot_color=sloot_color;
        this.sloot_state=sloot_state;
        this.company_number=company_number;
        this.name=name;
        this.state_change_date=state_change_date;
        this.floor_id=floor_id;
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

    public String getSloot_type() {
        return sloot_type;
    }

    public void setSloot_type(String sloot_type) {
        this.sloot_type = sloot_type;
    }

    public String getSloot_color() {
        return sloot_color;
    }

    public void setSloot_color(String sloot_color) {
        this.sloot_color = sloot_color;
    }

    public String getSloot_state() {
        return sloot_state;
    }

    public void setSloot_state(String sloot_state) {
        this.sloot_state = sloot_state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState_change_date() {
        return state_change_date;
    }

    public void setState_change_date(String state_change_date) {
        this.state_change_date = state_change_date;
    }

    public String getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(String floor_id) {
        this.floor_id = floor_id;
    }
}
