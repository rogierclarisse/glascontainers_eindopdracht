package com.example.glascontainers.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class CountrySingleton {

    //Singleton
    public static final CountrySingleton INSTANCE = new CountrySingleton();

    private CountrySingleton(){
    }

    private ArrayList<Country> countries;

    public ArrayList<Country> getCountries() {
        if(countries == null){
            countries = new ArrayList<>();
            countries.add(new Country(new LatLng(48.8584, 2.2945),
                    "France",
                    "Paris")
            );
            countries.add(new Country(new LatLng(51.528308, -0.381789),
                    "United Kingdom",
                    "London")
            );

            countries.add(new Country(new LatLng(60.1098678, 24.738504),
                    "Finland",
                    "Helsinki")
            );
        }
        return countries;
    }
}
