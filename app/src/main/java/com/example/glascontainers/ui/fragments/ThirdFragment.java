package com.example.glascontainers.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.example.glascontainers.databinding.FragmentFirstBinding;
import com.example.glascontainers.databinding.FragmentThirdBinding;
import com.google.android.gms.maps.MapView;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;

    private MapView mapView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mapView

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}