package com.example.glascontainers.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.example.glascontainers.databinding.FragmentFirstBinding;
import com.example.glascontainers.R;
import com.example.glascontainers.databinding.FragmentThirdBinding;
import com.example.glascontainers.model.ContainerLocation;
import com.example.glascontainers.model.ContainerViewModel;
import com.example.glascontainers.model.Country;
import com.example.glascontainers.model.CountrySingleton;
import com.example.glascontainers.ui.util.ContLocAdapter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;

    private MapView mapView;
    private GoogleMap myMap;
    //listeners
    private OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            //test


            myMap = googleMap;
           // ContainerViewModel cvm =
            //demo
            LatLng coordBrussel = new LatLng(50.858712, 4.347446);
            CameraUpdate moveToBrussels = CameraUpdateFactory.newLatLngZoom(coordBrussel, 12);

            myMap.animateCamera(moveToBrussels);
            myMap.getUiSettings().setZoomControlsEnabled(true);
            myMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

//            drawmarkers();
            drawlocations();
        }
    };

    //de markers tekenen van de landen die in de singleton gedeclareerd zijn.
//    private void drawmarkers(){
//        for(Country currentCountry : CountrySingleton.INSTANCE.getCountries()){
//            Marker currentMarker = myMap.addMarker(new MarkerOptions()
//                    .position(currentCountry.getCoordinate())
//                    .title(currentCountry.getName())
//                    .snippet(currentCountry.getCapital())
//            );
//            currentMarker.setTag(currentCountry);
//        }
//    }

    private void drawlocations() {
        ContainerViewModel containerViewModel = new ViewModelProvider(getActivity()).get(ContainerViewModel.class);
        containerViewModel.getContainerLocations().observe(getViewLifecycleOwner(), new Observer<ArrayList<ContainerLocation>>() {
            @Override
            public void onChanged(ArrayList<ContainerLocation> containerLocations) {
                for(ContainerLocation cl: containerLocations){

                    LatLng coordinate = new LatLng(cl.getGeo_coord0(), cl.getGeo_coord1());
                    Marker currentMarker2 = myMap.addMarker(new MarkerOptions()
                            .position(coordinate)
                            .title(cl.getDescription())
                    );
                    currentMarker2.setTag(cl);
                }

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

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

        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(onMapReadyCallback);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}