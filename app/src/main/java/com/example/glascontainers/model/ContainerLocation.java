package com.example.glascontainers.model;

import com.google.android.gms.maps.model.LatLng;

public class ContainerLocation {

    private String description;
    private double geo_coord0, geo_coord1;


    public ContainerLocation(String description, double geo_coord0, double geo_coord1) {
        this.description = description;
        this.geo_coord0 = geo_coord0;
        this.geo_coord1 = geo_coord1;
    }

    public ContainerLocation() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getGeo_coord0() {
        return geo_coord0;
    }

    public void setGeo_coord0(double geo_coord0) {
        this.geo_coord0 = geo_coord0;
    }

    public double getGeo_coord1() {
        return geo_coord1;
    }

    public void setGeo_coord1(double geo_coord1) {
        this.geo_coord1 = geo_coord1;
    }
}
