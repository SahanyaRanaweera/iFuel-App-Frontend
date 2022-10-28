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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuelQueueAdapter extends RecyclerView.Adapter<FuelQueueAdapter.userViewHolder> {

    Context context;
    List<FuelQueue> fuelQueue;
    private ItemClickListener clickListener;

    public FuelQueueAdapter(Context context, List<FuelQueue> fuelQueue) {
        this.context = context;
        this.fuelQueue = fuelQueue;
    }

    public void setFuelQueue(List<FuelQueue> fuelQueue) {
        this.fuelQueue = fuelQueue;
        notifyDataSetChanged();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public userViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fuel_queue,parent,false);
        return new userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(userViewHolder holder, int position) {
        holder.name.setText(fuelQueue.get(position).getName().toString());
        if(fuelQueue.get(position).getFuelPumpStatus())
            holder.pumpStatus.setText("Pumped");
        else
            holder.pumpStatus.setText("Not Pumped");

        holder.update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent i = new Intent(context,UpdatePumpStatus.class);
                i.putExtra("queue_id", fuelQueue.get(position).getId().toString());
                context.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<FuelQueue> call = apiService.deleteFuelQueue(fuelQueue.get(position).getId());

                call.enqueue(new Callback<FuelQueue>() {
                    @Override
                    public void onResponse(Call<FuelQueue> call, Response<FuelQueue> response) {
                        FuelQueue responseFromAPI = response.body();
                        Log.d("TAG","Response DEL = "+ response.body());
                    }

                    @Override
                    public void onFailure(Call<FuelQueue> call, Throwable t) {
                        Log.d("TAG","Response = "+t.toString());
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if(fuelQueue != null){
            return fuelQueue.size();
        }
        return 0;

    }

    public class userViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition()); // call the onClick in the OnItemClickListener
        }

    }

}