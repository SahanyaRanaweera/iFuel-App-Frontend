package com.example.ifuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.ifuelapp.models.FuelQueue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToQueue extends AppCompatActivity {
    EditText etstation, etvehicletype, etarrivetime;
    String vehicletype;
    String station;
    String arrivetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_arrival_time);
        etstation =  findViewById(R.id.etstation);
        etvehicletype =  findViewById(R.id.etvehicletype);
        etarrivetime =  findViewById(R.id.etarrivetime);

    }

    public void updatePumpStatus(View view){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        station= etstation.getText().toString();
        vehicletype=etvehicletype.getText().toString();
        arrivetime =etarrivetime.getText().toString();

        FuelQueue modal = new FuelQueue(station, vehicletype, arrivetime);
        Call<FuelQueue> call = apiService.addFuelQueue(modal);

        call.enqueue(new Callback<FuelQueue>() {
            @Override
            public void onResponse(Call<FuelQueue> call, Response<FuelQueue> response) {
                FuelQueue responseFromAPI = response.body();
                Log.d("TAG","Response ADD = "+ response.body());
            }

            @Override
            public void onFailure(Call<FuelQueue> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
        this.finish();
    }

}