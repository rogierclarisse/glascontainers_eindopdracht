package com.example.glascontainers.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.glascontainers.databinding.FragmentSecondBinding;
import com.example.glascontainers.model.ContainerLocation;
import com.example.glascontainers.model.ContainerViewModel;

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

        //hier spreken we het viewmodel aan om zo de data in dit fragment te kunnen tonen

        ContainerViewModel containerViewModel = new ViewModelProvider(getActivity()).get(ContainerViewModel.class);
        containerViewModel.getContainerLocations().observe(getViewLifecycleOwner(), new Observer<ArrayList<ContainerLocation>>() {
            @Override
            public void onChanged(ArrayList<ContainerLocation> containerLocations) {
                Log.d("TEST", "Data Loaded");
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}