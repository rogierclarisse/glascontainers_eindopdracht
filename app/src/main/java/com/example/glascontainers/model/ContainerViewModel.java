package com.example.glascontainers.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

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
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient mClient = new OkHttpClient();

                    Request mRequest = new Request.Builder()
                            .url("https://jsonplaceholder.typicode.com/posts")
                            .get()
                            .build();

                    Response mResponse = mClient.newCall(mRequest).execute();

                    Log.d("TEST", mResponse.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return containerLocations;
    }
}
