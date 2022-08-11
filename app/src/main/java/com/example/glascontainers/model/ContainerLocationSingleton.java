package com.example.glascontainers.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.ArrayList;

public class ContainerLocationSingleton{

    public static final ContainerLocationSingleton INSTANCE = new ContainerLocationSingleton();


    public ContainerLocationSingleton() {
    }

    private ArrayList<ContainerLocation> locations;



    public ArrayList<ContainerLocation> getLocations() {
        if(locations == null){
//            ContainerViewModel containerViewModel = new ViewModelProvider(getActivity()).get(ContainerViewModel.class);
            //hier moeten we over de locaties loopen en die in de lijst zetten


        }
        return locations;
    }


}
