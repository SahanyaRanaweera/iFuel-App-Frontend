package com.example.ifuelapp;

import com.example.ifuelapp.models.FuelQueue;
import com.example.ifuelapp.models.FuelStation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    //FuelStation APIs
    @GET("FuelStation/")
    Call<List<FuelStation>> getFuelStations();
    @GET("FuelStation/{id}")
    Call<FuelStation> getFuelStation(@Path("id") String id);
    @POST("FuelStation/")
    Call<FuelStation> addFuelStation(@Body FuelStation dataModal);
    @DELETE("FuelStation/{id}")
    Call<FuelStation> deleteFuelStation(@Path("id") String id);

    //FuelQueue APIs
    @GET("FuelQueue/")
    Call<List<FuelQueue>> getFuelQueue();
    @PUT("FuelQueue/{id}")
    Call<FuelQueue> updateFuelPumpStatus(@Path("id") String id, @Body FuelQueue dataModal);
    @POST("FuelQueue/")
    Call<FuelQueue> addFuelQueue(@Body FuelQueue dataModal);
    @DELETE("FuelQueue/{id}")
    Call<FuelQueue> deleteFuelQueue(@Path("id") String id);
}
