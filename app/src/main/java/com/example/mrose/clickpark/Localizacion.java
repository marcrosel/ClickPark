package com.example.mrose.clickpark;

public class Localizacion {
    //atributos
    int id, postal_code;
    String street_adress, city, state_province, lat, lon;

    //constructor
    public Localizacion(int id, String street_adress, int postal_code, String city, String state_province, String lat, String lon){
        this.id=id;
        this.street_adress=street_adress;
        this.postal_code=postal_code;
        this.city=city;
        this.state_province=state_province;
        this.lat=lat;
        this.lon=lon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public String getStreet_adress() {
        return street_adress;
    }

    public void setStreet_adress(String street_adress) {
        this.street_adress = street_adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_province() {
        return state_province;
    }

    public void setState_province(String state_province) {
        this.state_province = state_province;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
