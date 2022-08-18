package com.example.glascontainers.dao;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.glascontainers.model.ContainerLocation;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface LocationDao {
    @Query("SELECT * FROM ContainerLocation")
    List<ContainerLocation> getAllContainerLocations();

    @Delete
    void deleteContainerLocation(ContainerLocation containerLocation);

    @Insert
    void insertContainerLocation(ContainerLocation containerLocation);

    @Update
    void updateContainerLocation(ContainerLocation containerLocation);
}
