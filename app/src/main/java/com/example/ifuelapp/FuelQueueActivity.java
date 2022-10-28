package com.example.ifuelapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ifuelapp.models.FuelQueue;
import com.example.ifuelapp.models.FuelStation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuelQueueActivity extends AppCompatActivity {
    List<FuelQueue> fuelQueues;
    TextView location;
    RecyclerView recyclerView;
    FuelQueueAdapter fuelQueueAdapter;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_fuel_queue);
        fuelQueues = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.fuelQueue);
        location = (TextView) findViewById(R.id.textView10);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fuelQueueAdapter = new FuelQueueAdapter(getApplicationContext(), fuelQueues);
        recyclerView.setAdapter(fuelQueueAdapter);
        button = (Button)findViewById(R.id.button);

        Intent intent = getIntent();
        String id = intent.getStringExtra("station_id");
        String station_location = intent.getStringExtra("location");

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<FuelQueue>> call = apiService.getFuelQueue();

        call.enqueue(new Callback<List<FuelQueue>>() {
            @Override
            public void onResponse(Call<List<FuelQueue>> call, Response<List<FuelQueue>> response) {
                Log.d("TAG","Response = "+ response.body());

//                List<FuelQueue> filteredList = new ArrayList<>();
//                for (FuelQueue queue : response.body()) {
//                    if (queue.getStationId().equals(id)) {
//                        filteredList.add(queue);
//                    }
//                }
                fuelQueues = response.body();
                location.setText(station_location);
                fuelQueueAdapter.setFuelQueue(fuelQueues);
            }

            @Override
            public void onFailure(Call<List<FuelQueue>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }


        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuelQueueActivity.this,AddToQueue.class);
                startActivity(intent);
            }
        });
    }

}