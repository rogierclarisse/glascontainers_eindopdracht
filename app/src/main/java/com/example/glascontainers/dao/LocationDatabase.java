package com.example.glascontainers.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.glascontainers.model.ContainerLocation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, entities = {ContainerLocation.class}, exportSchema = false)
public abstract class LocationDatabase extends RoomDatabase {
    private static final String DB_NAME = "locationDatabase.db";
    private static LocationDatabase instance;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LocationDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context,
                    LocationDatabase.class,
                    DB_NAME).build();

        }
        return instance;
    }

//    public static LocationDatabase getInstance(Context context) {
//        if(instance == null){
//            instance = create(context);
//        }
//        return instance;
//    }

    private static LocationDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                LocationDatabase.class,
                DB_NAME).allowMainThreadQueries().build();
    }

    public abstract LocationDao getRepoDao();


}
