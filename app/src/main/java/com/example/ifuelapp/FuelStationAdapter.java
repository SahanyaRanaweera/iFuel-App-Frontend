package com.example.ifuelapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ifuelapp.models.FuelStation;

import java.util.List;

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

        public userViewHolder(View itemView) {
            super(itemView);
            location = (TextView)itemView.findViewById(R.id.location);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition()); // call the onClick in the OnItemClickListener
        }
    }
}