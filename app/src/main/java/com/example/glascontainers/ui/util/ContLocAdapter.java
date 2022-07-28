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

//        private final TextView recyclerViewTitle;
        private final TextView titleTV;
        private final TextView coordTV;

        public ContLocHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.tv_title);
            coordTV = itemView.findViewById(R.id.tv_coordinates);
//            recyclerViewTitle = itemView.findViewById(R.id.tv_RecyclerTitle);
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

//        String coord_contraction =
        ContainerLocation containerLocation = data.get(position);


        holder.titleTV.setText(containerLocation.getDescription());
        holder.coordTV.setText(containerLocation.getGeo_coord0()+", "+containerLocation.getGeo_coord1());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
