package com.mycompany.app.models;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Address {
    @BsonProperty("index")
    private Integer index;
    @BsonProperty("city")
    private String city;
    @BsonProperty("street")
    private String street;
    @BsonProperty("building")
    private String building;
    @BsonProperty("flat")
    private String flat;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    @Override
    public String toString() {
        return "Address{" +
                "index=" + index +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", flat='" + flat + '\'' +
                '}';
    }
}
