package com.example.ifuelapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ifuelapp.models.FuelQueue;

import java.util.List;

public class FuelQueueAdapter extends RecyclerView.Adapter<FuelQueueAdapter.userViewHolder> {

    Context context;
    List<FuelQueue> fuelQueue;

    public FuelQueueAdapter(Context context, List<FuelQueue> fuelQueue) {
        this.context = context;
        this.fuelQueue = fuelQueue;
    }

    public void setFuelQueue(List<FuelQueue> fuelQueue) {
        this.fuelQueue = fuelQueue;
        notifyDataSetChanged();
    }

    @Override
    public userViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fuel_queue,parent,false);
        return new userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(userViewHolder holder, int position) {
        holder.name.setText(fuelQueue.get(position).getName().toString());
        holder.pumpStatus.setText(fuelQueue.get(position).getPumpStatus().toString());
    }

    @Override
    public int getItemCount() {
        if(fuelQueue != null){
            return fuelQueue.size();
        }
        return 0;

    }

    public class userViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView pumpStatus;
        Button update;
        Button delete;
        public userViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            pumpStatus = (TextView)itemView.findViewById(R.id.pumpStatus);
            update = (Button)itemView.findViewById(R.id.update_button);
            delete = (Button)itemView.findViewById(R.id.delete_button);
        }


    }
}