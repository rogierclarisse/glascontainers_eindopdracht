package com.example.glascontainers.ui.util;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glascontainers.R;
import com.example.glascontainers.model.ContainerLocation;

import java.util.ArrayList;

public class ContLocAdapter extends RecyclerView.Adapter<ContLocAdapter.ContLocHolder> {


    class ContLocHolder extends RecyclerView.ViewHolder{

        private final TextView titleTV;
        private final TextView coordTV;
        private ContainerLocation contLoc;
//        private final Button viewIdbutton;
//
        //object
//        ContainerLocation containerLocation;

        public ContLocHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.tv_title);
            coordTV = itemView.findViewById(R.id.tv_coordinates);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("test", "er wordt op de knop gedrukt");
                }
            });

            itemView.findViewById(R.id.bt_viewid).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("test", "er wordt op de knop gedrukt met description ");
                }
            });


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
//        holder.viewIdbutton.setText(containerLocation.getDescription());


//        holder.containerLocation = containerLocation;

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
