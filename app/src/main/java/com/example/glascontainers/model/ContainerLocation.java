package com.example.glascontainers.model;

import com.google.android.gms.maps.model.LatLng;

public class ContainerLocation {

    private String description;
    private LatLng geo_coord;

    public ContainerLocation(String description, LatLng geo_coord) {
        this.description = description;
        this.geo_coord = geo_coord;
    }

    public ContainerLocation() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LatLng getGeo_coord() {
        return geo_coord;
    }

    public void setGeo_coord(LatLng geo_coord) {
        this.geo_coord = geo_coord;
    }
}
