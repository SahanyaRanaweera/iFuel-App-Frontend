package com.example.ifuelapp.models;

import com.google.gson.annotations.SerializedName;

public class FuelQueue {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("pumpStatus")
    private String pumpStatus;

    public FuelQueue(String id, String name, String pumpStatus) {
        this.id = id;
        this.name = name;
        this.pumpStatus = pumpStatus;
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

    public String getPumpStatus() {
        return pumpStatus;
    }

    public void setPumpStatus(String pumpStatus) {
        this.pumpStatus = pumpStatus;
    }
}
