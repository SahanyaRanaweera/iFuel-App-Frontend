package com.example.ifuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.ifuelapp.models.FuelQueue;
import com.example.ifuelapp.models.FuelStation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFuelStation extends AppCompatActivity {
    EditText etowner, etlocation;
    String owner;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_fuel_station);
        etowner =  findViewById(R.id.etowner);
        etlocation =  findViewById(R.id.etlocation);

    }

    public void addFuelStation(View view){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        owner= etowner.getText().toString();
        location=etlocation.getText().toString();

        FuelStation modal = new FuelStation( location);
        Call<FuelStation> call = apiService.addFuelStation(modal);

        call.enqueue(new Callback<FuelStation>() {
            @Override
            public void onResponse(Call<FuelStation> call, Response<FuelStation> response) {
                FuelStation responseFromAPI = response.body();
                Log.d("TAG","Response ADD = "+ response.body());
            }

            @Override
            public void onFailure(Call<FuelStation> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
        this.finish();
    }

}