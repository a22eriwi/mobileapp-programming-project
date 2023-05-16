package com.example.project;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Cities> datalistan;
    public RecyclerViewAdapter(List<Cities> datalistan) {
        this.datalistan = datalistan;
    }
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(datalistan.get(position).getName());
        holder.size.setText(String.valueOf(datalistan.get(position).getSize()));
    }

    @Override
    public int getItemCount() {
        return datalistan.size();
    }

}