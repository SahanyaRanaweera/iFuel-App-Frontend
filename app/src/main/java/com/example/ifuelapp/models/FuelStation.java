package com.example.ifuelapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FuelStation {

    @SerializedName("id")
    private String id;
    @SerializedName("location")
    private String location;
    @SerializedName("fuelStatuses")
    private List<Fuel> fuelStatuses;

    public FuelStation(String id, String location, List<Fuel> fuelStatuses) {
        this.id = id;
        this.location = location;
        this.fuelStatuses = fuelStatuses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Fuel> getFuelStatuses() {
        return fuelStatuses;
    }

    public void setFuelStatuses(List<Fuel> fuelStatuses) {
        this.fuelStatuses = fuelStatuses;
    }
}
