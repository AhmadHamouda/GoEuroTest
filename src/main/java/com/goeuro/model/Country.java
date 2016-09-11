/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goeuro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author ahmad
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    @JsonProperty("_id")
    private int id;
    private String name;
    private String type;
    @JsonProperty("geo_position")
    private GeoPosition geoPosition;

    public Country() {
    }

    public Country(int id, String name, String type, GeoPosition geoPosition) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.geoPosition = geoPosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + type + "," + geoPosition.getLatitude() + "," + geoPosition.getLongitude();
    }

}
