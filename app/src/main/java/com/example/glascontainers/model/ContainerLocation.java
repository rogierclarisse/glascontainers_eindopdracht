package com.example.glascontainers.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

@Entity
public class ContainerLocation implements Serializable {

    //primary key achteraf toegevoegd voor het maken van de database
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String description;
    private String recordId;
    private double geo_coord0, geo_coord1;



    public ContainerLocation(String description, String recordId, double geo_coord0, double geo_coord1) {
        this.description = description;
        this.recordId = recordId;
        this.geo_coord0 = geo_coord0;
        this.geo_coord1 = geo_coord1;
    }

    public ContainerLocation() {
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //extra getter voor primary key, setter niet nodig want automatisch
    public Long getId() {
        return id;
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
