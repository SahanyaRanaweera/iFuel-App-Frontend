package com.example.ifuelapp.models;

import com.google.gson.annotations.SerializedName;

public class FuelQueue {

    @SerializedName("id")
    private String id;
    @SerializedName("vehicleName")
    private String name;
    @SerializedName("fuelPumpStatus")
    private boolean fuelPumpStatus;
    @SerializedName("stationId")
    private String stationId;
    @SerializedName("departureTime")
    private String departureTime;
    @SerializedName("arrivaltime")
    private String arrivetime;

    public FuelQueue(String id, String name, boolean fuelPumpStatus, String stationId) {
        this.id = id;
        this.name = name;
        this.fuelPumpStatus = fuelPumpStatus;
        this.stationId = stationId;
    }

    public FuelQueue(boolean fuelPumpStatus, String departureTime) {
        this.fuelPumpStatus = fuelPumpStatus;
        this.departureTime = departureTime;
    }

    public FuelQueue(String station, String vehicletype, String arrivetime) {
        this.stationId = station;
        this.name = vehicletype;
        this.arrivetime = arrivetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getFuelPumpStatus() {
        return fuelPumpStatus;
    }

    public void setFuelPumpStatus(boolean fuelPumpStatus) {
        this.fuelPumpStatus = fuelPumpStatus;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }
}
