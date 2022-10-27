package com.example.ifuelapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ifuelapp.models.Fuel;

import java.util.List;

public class FuelAdapter extends RecyclerView.Adapter<FuelAdapter.userViewHolder> {

    Context context;
    List<Fuel> fuelList;
    private ItemClickListener clickListener;

    public FuelAdapter(Context context, List<Fuel> fuelList) {
        this.context = context;
        this.fuelList = fuelList;
    }

    public void setFuelList(List<Fuel> fuelList) {
        this.fuelList = fuelList;
        notifyDataSetChanged();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public userViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fuel,parent,false);
        return new userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(userViewHolder holder, int position) {
        holder.fuelType.setText(fuelList.get(position).getFuelType().toString());
    }

    @Override
    public int getItemCount() {
        if(fuelList != null){
            return fuelList.size();
        }
        return 0;

    }

    public class userViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView fuelType;
        Button update;
        Button delete;
        public userViewHolder(View itemView) {
            super(itemView);
            fuelType = (TextView)itemView.findViewById(R.id.fuel_type);
            update = (Button)itemView.findViewById(R.id.update_button);
            delete = (Button)itemView.findViewById(R.id.delete_button);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition()); // call the onClick in the OnItemClickListener
        }
    }
}