package com.example.gymlog.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.gymlog.database.entities.GymLog;

import java.util.List;

@Dao
public interface GymLogDAO {

    @Insert
    void insert(GymLog log);

    @Query("SELECT * FROM GymLog ORDER BY date DESC")
    LiveData<List<GymLog>> getAllLogs();

    @Query("SELECT * FROM GymLog WHERE userId = :userId ORDER BY date DESC")
    LiveData<List<GymLog>> getLogsForUser(int userId);
}
