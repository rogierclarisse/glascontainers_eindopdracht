package com.example.glascontainers.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

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
    private MutableLiveData<ArrayList<ContainerLocation>> containerLocations;
    //threadpool aanmaken
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(2);

    public ContainerViewModel(@NonNull Application application) {
        super(application);
        containerLocations = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<ContainerLocation>> getContainerLocations() {
        ArrayList<ContainerLocation> containerLocationArrayList = new ArrayList<>();

        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient mClient = new OkHttpClient();

                    Request mRequest = new Request.Builder()
                            .url("https://opendata.brussel.be/api/records/1.0/search/?dataset=glass-containers&q=")
                            .get()
                            .build();

                    Response mResponse = mClient.newCall(mRequest).execute();

                    //json naar string
                    String responsePlainText = mResponse.body().string();
                    //string naar object
                    JSONObject locationsObject = new JSONObject(responsePlainText);
                    //juiste array uit het object halen
                    JSONArray recordsArray = locationsObject.getJSONArray("records");
//                    JSONObject recordsObject = locationsObject.getJSONObject("records");


                    //de lengte van recordsArray bepalen
                    int nObjects = recordsArray.length();
                    int i = 0;

                    //lo
                    while(i < nObjects){
                        JSONObject currentJSONElement = recordsArray.getJSONObject(i);

//                        ContainerLocation currentLocation = new ContainerLocation(
//                                currentJSONElement.getString("datasetid")
//                                currentJSONElement.ge
//                        );
                        JSONObject field = currentJSONElement.getJSONObject("fields");
//                        ContainerLocation currentLocation = new ContainerLocation(
//                                field.getString("description")
//                        );
                        JSONArray coordinaten = field.getJSONArray("geo_coord");
                        ContainerLocation currentLocation = new ContainerLocation(
//                                coordinaten.get(0),
//                                coordinaten.get(1)
                        );

                        containerLocationArrayList.add(currentLocation);
                        i++;
                    }

//                    Log.d("TEST", mResponse.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                containerLocations.postValue(containerLocationArrayList);

            }
        });
        return containerLocations;
    }
}
