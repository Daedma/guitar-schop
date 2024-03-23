package com.mycompany.app.models;

public class Address {
    private Integer index;
    private String city;
    private String building;
    private Integer flat;

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

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    @Override
    public String toString() {
        return "Address{" +
                "index=" + index +
                ", city='" + city + '\'' +
                ", building='" + building + '\'' +
                ", flat=" + flat +
                '}';
    }
}
