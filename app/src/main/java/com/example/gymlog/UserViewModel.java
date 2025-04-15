package com.example.gymlog;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gymlog.database.GymLogRepository;
import com.example.gymlog.database.entities.User;

public class UserViewModel extends AndroidViewModel {

    private final GymLogRepository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new GymLogRepository(application);
    }

    public LiveData<User> getUser(String username, String password) {
        return repository.getUser(username, password);
    }
}
