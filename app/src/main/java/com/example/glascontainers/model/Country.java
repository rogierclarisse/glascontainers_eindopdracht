package com.example.glascontainers.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Country implements Serializable {

    private LatLng coordinate;
    private String name, capital;

    public Country(LatLng coordinate, String name, String capital) {
        this.coordinate = coordinate;
        this.name = name;
        this.capital = capital;
    }

    public LatLng getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(LatLng coordinate) {
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
