package com.example.glascontainers.model;

import android.app.Application;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.glascontainers.dao.LocationDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ContainerViewModel extends AndroidViewModel {

    //object voor data die we binnentrekken om data in applicatie te tonen
    private MutableLiveData<ArrayList<ContainerLocation>> containerLocations; //verander van publc naar private om er in singleton aan te kunnen
    //threadpool aanmaken
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(2);

    //declareren van database
    private LocationDatabase database;

    public ContainerViewModel(@NonNull Application application) {
        super(application);
        containerLocations = new MutableLiveData<>();
        //database
        database = LocationDatabase.getInstance(application);
        //containerLocations = (MutableLiveData<ArrayList<ContainerLocation>>) database.getRepoDao().getAllContainerLocations();
    }

    //methode om locaties in de database op te slaan, op te roepen in getContainerLocations
    public void insertLocation(ContainerLocation cl){
        LocationDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                database.getRepoDao().insertContainerLocation(cl);
            }
        });
    }

    public MutableLiveData<ArrayList<ContainerLocation>> getContainerLocations() {
        ArrayList<ContainerLocation> containerLocationArrayList = new ArrayList<>();

        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient mClient = new OkHttpClient();

                    Request mRequest = new Request.Builder()
                            .url("https://opendata.brussel.be/api/records/1.0/search/?dataset=glass-containers&q=&rows=100")
                            .get()
                            .build();

                    Response mResponse = mClient.newCall(mRequest).execute();

                    //json naar string
                    String responsePlainText = mResponse.body().string();
                    //string naar object
                    JSONObject locationsObject = new JSONObject(responsePlainText);
                    //juiste array uit het object halen
                    JSONArray recordsArray = locationsObject.getJSONArray("records");


                    //de lengte van recordsArray bepalen
                    int nObjects = recordsArray.length();
                    int i = 0;

                    //lo
                    while(i < nObjects){
                        JSONObject currentJSONElement = recordsArray.getJSONObject(i);
                        JSONObject field = currentJSONElement.getJSONObject("fields");
                        JSONArray coordinaten = field.getJSONArray("geo_coord");

                        //hieronder maken we een nieuw ContainerLocation-object aan en
                        // geven we die waarden uit de JSON
                        ContainerLocation currentLocation = new ContainerLocation(
//
                                field.getString("description0"),
                                currentJSONElement.getString("recordid"),
                                coordinaten.getDouble(0),
                                coordinaten.getDouble(1)
                        );

                        //object wordt in de ArrayList gezet
                        containerLocationArrayList.add(currentLocation);
                        //object in database zetten
                        insertLocation(currentLocation);
                        i++;
                    }

//                    Log.d("TEST", mResponse.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Arraylist wordt in MutableLiveData gezet
                containerLocations.postValue(containerLocationArrayList);

            }
        });
        return containerLocations;
    }
}
