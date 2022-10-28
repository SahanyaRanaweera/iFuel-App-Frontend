package com.example.ifuelapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ifuelapp.models.FuelQueue;
import com.example.ifuelapp.models.FuelStation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuelStationAdapter extends RecyclerView.Adapter<FuelStationAdapter.userViewHolder> {

    Context context;
    List<FuelStation> fuelStationList;
    private ItemClickListener clickListener;

    public FuelStationAdapter(Context context, List<FuelStation> fuelStationList) {
        this.context = context;
        this.fuelStationList = fuelStationList;
    }

    public void setFuelStationList(List<FuelStation> fuelStationList) {
        this.fuelStationList = fuelStationList;
        notifyDataSetChanged();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public userViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fuel_station,parent,false);
        return new userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(userViewHolder holder, int position) {
        holder.location.setText(fuelStationList.get(position).getLocation().toString());
        holder.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<FuelStation> call = apiService.deleteFuelStation(fuelStationList.get(position).getId());

                call.enqueue(new Callback<FuelStation>() {
                    @Override
                    public void onResponse(Call<FuelStation> call, Response<FuelStation> response) {
                        FuelStation responseFromAPI = response.body();
                        Log.d("TAG","Response DEL = "+ response.body());
                    }

                    @Override
                    public void onFailure(Call<FuelStation> call, Throwable t) {
                        Log.d("TAG","Response = "+t.toString());
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if(fuelStationList != null){
            return fuelStationList.size();
        }
        return 0;

    }

    public class userViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView location;
        Button delete;

        public userViewHolder(View itemView) {
            super(itemView);
            location = (TextView)itemView.findViewById(R.id.location);
            delete = (Button)itemView.findViewById(R.id.delete_button);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition()); // call the onClick in the OnItemClickListener
        }
    }
}