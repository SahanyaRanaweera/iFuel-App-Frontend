package com.example.ifuelapp;

import com.example.ifuelapp.models.FuelQueue;
import com.example.ifuelapp.models.FuelStation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("FuelStation/")
    Call<List<FuelStation>> getFuelStations();
    @GET("FuelStation/{id}")
    Call<FuelStation> getFuelStation(@Path("id") String id);
    @GET("FuelQueue/{id}")
    Call<List<FuelQueue>> getFuelQueue(@Path("id") String id);
}
