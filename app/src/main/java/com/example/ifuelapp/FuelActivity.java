package com.example.ifuelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ifuelapp.models.Fuel;
import com.example.ifuelapp.models.FuelStation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuelActivity extends AppCompatActivity implements ItemClickListener{
    List<Fuel> fuelList;
    FuelStation fuelStation;
    TextView location;
    RecyclerView recyclerView;
    FuelAdapter fuelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fuel_station);
        fuelList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.fuelList);
        location = (TextView) findViewById(R.id.textView10);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fuelAdapter = new FuelAdapter(getApplicationContext(), fuelList);
        recyclerView.setAdapter(fuelAdapter);

        Intent intent = getIntent();
        String id = intent.getStringExtra("station_id");

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<FuelStation> call = apiService.getFuelStation(id);

        call.enqueue(new Callback<FuelStation>() {
            @Override
            public void onResponse(Call<FuelStation> call, Response<FuelStation> response) {
                fuelList = response.body().getFuelStatuses();
                fuelStation = response.body();
                Log.d("TAG","Response = "+ response.body().getFuelStatuses());
                location.setText(response.body().getLocation().toString());
                fuelAdapter.setFuelList(fuelList);
            }

            @Override
            public void onFailure(Call<FuelStation> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }

        });
        fuelAdapter.setClickListener(this);

    }

    @Override
    public void onClick(View view, int position) {
        Intent i = new Intent(this, FuelQueueActivity.class);
        i.putExtra("station_id", fuelStation.getId().toString());
        i.putExtra("location", fuelStation.getLocation().toString());

        Log.i("hello", fuelList.get(position).getId().toString());
        startActivity(i);
    }
}