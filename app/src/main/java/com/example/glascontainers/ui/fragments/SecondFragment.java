package com.example.glascontainers.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.glascontainers.R;
import com.example.glascontainers.databinding.FragmentSecondBinding;
import com.example.glascontainers.model.ContainerLocation;
import com.example.glascontainers.model.ContainerViewModel;
import com.example.glascontainers.ui.util.ContLocAdapter;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //recycler view aanspreken
        RecyclerView rvLocations = view.findViewById(R.id.rv_locations);

        TextView title = view.findViewById(R.id.tv_RecyclerTitle);
//        Button viewId = view.findViewById(R.id.bt_viewid);
        title.setText("Locations");
//        viewId.setText("view id");
        //horizontale lijnen
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvLocations.setLayoutManager(mLayoutManager);

        //hier spreken we het viewmodel aan om zo de data in dit fragment te kunnen tonen

        ContainerViewModel containerViewModel = new ViewModelProvider(getActivity()).get(ContainerViewModel.class);
        containerViewModel.getContainerLocations().observe(getViewLifecycleOwner(), new Observer<ArrayList<ContainerLocation>>() {
            @Override
            public void onChanged(ArrayList<ContainerLocation> containerLocations) {

                ContLocAdapter mAdapter = new ContLocAdapter(containerLocations);
                rvLocations.setAdapter(mAdapter);

            }
        });






    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}