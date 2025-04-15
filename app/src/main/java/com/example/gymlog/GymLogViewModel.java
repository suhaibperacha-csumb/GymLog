package com.example.gymlog;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gymlog.database.GymLogRepository;
import com.example.gymlog.database.entities.GymLog;
import com.example.gymlog.database.entities.User;

import java.util.List;

public class GymLogViewModel extends AndroidViewModel {

    private final GymLogRepository repository;
    private final LiveData<List<User>> allUsers;

    public GymLogViewModel(@NonNull Application application) {
        super(application);
        repository = new GymLogRepository(application);
        allUsers = repository.getAllUsers();
    }

    public LiveData<List<GymLog>> getLogsForUser(int userId) {
        return repository.getLogsForUser(userId);
    }

    public void insert(GymLog log) {
        repository.insert(log);
    }

    public void insertUser(User user) {
        repository.insertUser(user);
    }

    public LiveData<User> getUser(String username, String password) {
        return repository.getUser(username, password);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
}
