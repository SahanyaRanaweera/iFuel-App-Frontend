package com.example.ifuelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ifuelapp.models.FuelStation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuelStationActivity extends AppCompatActivity implements ItemClickListener{
    List<FuelStation> fuelStationList;
    RecyclerView recyclerView;
    FuelStationAdapter fuelStationAdapter;
    Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_fuel_station);
        fuelStationList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.fuelStationList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fuelStationAdapter = new FuelStationAdapter(getApplicationContext(), fuelStationList);
        recyclerView.setAdapter(fuelStationAdapter);
        addbtn = (Button)findViewById(R.id.addbtn);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<FuelStation>> call = apiService.getFuelStations();

        call.enqueue(new Callback<List<FuelStation>>() {
            @Override
            public void onResponse(Call<List<FuelStation>> call, Response<List<FuelStation>> response) {
                fuelStationList = response.body();
                Log.d("TAG","Response = "+ response.body());
                fuelStationAdapter.setFuelStationList(fuelStationList);
            }

            @Override
            public void onFailure(Call<List<FuelStation>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
        fuelStationAdapter.setClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        Intent i = new Intent(this, FuelActivity.class);
        i.putExtra("station_id", fuelStationList.get(position).getId().toString());

        Log.i("hello", fuelStationList.get(position).getId().toString());
        startActivity(i);
    }
    @Override
    protected void onResume() {
        super.onResume();
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuelStationActivity.this,AddFuelStation.class);
                startActivity(intent);
            }
        });
    }
}