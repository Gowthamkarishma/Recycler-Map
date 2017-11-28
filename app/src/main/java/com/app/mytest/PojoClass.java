package com.app.mytest;

import java.io.Serializable;

public class PojoClass implements Serializable {

    private String name;
    private String image;
    private double lat;
    private double lng;
    private boolean aBoolean;

    public PojoClass(String name, String image, double lat, double lng, boolean aBoolean) {
        this.name = name;
        this.image = image;
        this.lat = lat;
        this.lng = lng;
        this.aBoolean = aBoolean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
}
