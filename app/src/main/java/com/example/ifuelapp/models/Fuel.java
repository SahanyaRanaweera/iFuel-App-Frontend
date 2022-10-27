package com.example.ifuelapp.models;

import com.google.gson.annotations.SerializedName;

public class Fuel {

    @SerializedName("id")
    private String id;
    @SerializedName("fuelType")
    private String fuelType;
    @SerializedName("availabilityStatus")
    private String availabilityStatus;

    public Fuel(String id, String fuelType, String availabilityStatus) {
        this.id = id;
        this.fuelType = fuelType;
        this.availabilityStatus = availabilityStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}
