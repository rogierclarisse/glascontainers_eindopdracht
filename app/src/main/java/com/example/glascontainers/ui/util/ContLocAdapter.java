package com.example.glascontainers.ui.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glascontainers.R;
import com.example.glascontainers.model.ContainerLocation;

import java.util.ArrayList;

public class ContLocAdapter extends RecyclerView.Adapter<ContLocAdapter.ContLocHolder> {


    class ContLocHolder extends RecyclerView.ViewHolder{

        private final TextView titleTV;

        public ContLocHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.rv_title);
//            bodyTV = itemView.findViewById(R.id.rv_body);
        }
    }

    ArrayList<ContainerLocation> data;

    public ContLocAdapter(ArrayList<ContainerLocation> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ContLocHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //parent pakt inflator om layout op scherm te tekenen
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        return new ContLocHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ContLocHolder holder, int position) {

        ContainerLocation containerLocation = data.get(position);

        holder.titleTV.setText(containerLocation.getDescription());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
