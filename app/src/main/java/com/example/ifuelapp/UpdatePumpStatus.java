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

public class UpdatePumpStatus extends AppCompatActivity {
    EditText etstation, etvehicletype, etpumpstatus, etdeparttime;
    String vehicletype;
    String station;
    String departtime;
    boolean pumpstatus;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_departure_time);
        etstation =  findViewById(R.id.etstation);
        etvehicletype =  findViewById(R.id.etvehicletype);
        etpumpstatus =  findViewById(R.id.etpumpstatus);
        etdeparttime =  findViewById(R.id.etdeparttime);

        Intent intent = getIntent();
        id = intent.getStringExtra("queue_id");
    }

    public void updatePumpStatus(View view){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        station= etstation.getText().toString();
        vehicletype=etvehicletype.getText().toString();
        departtime =etdeparttime.getText().toString();
        if(etpumpstatus.getText().toString().equals("Y"))
            pumpstatus = true;
        else
            pumpstatus = false;

        FuelQueue modal = new FuelQueue(pumpstatus, departtime);
        Call<FuelQueue> call = apiService.updateFuelPumpStatus(id, modal);

        call.enqueue(new Callback<FuelQueue>() {
            @Override
            public void onResponse(Call<FuelQueue> call, Response<FuelQueue> response) {
                FuelQueue responseFromAPI = response.body();
                Log.d("TAG","Response UPD = "+ response.body() + id);
            }

            @Override
            public void onFailure(Call<FuelQueue> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
        this.finish();
    }

}