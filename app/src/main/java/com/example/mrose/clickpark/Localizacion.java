package com.example.mrose.clickpark;

public class Localizacion {
    //atributos
    int id, postal_code;
    String street_address, city, state_province, latitude, longitude;

    //constructor
    public Localizacion(int id, String street_adress, int postal_code, String city, String state_province, String lat, String lon){
        this.id=id;
        this.street_address=street_adress;
        this.postal_code=postal_code;
        this.city=city;
        this.state_province=state_province;
        this.latitude=lat;
        this.longitude=lon;
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

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_adress(String street_adress) {
        this.street_address = street_adress;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String lat) {
        this.latitude = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String lon) {
        this.longitude = lon;
    }
}
