package com.example.gymlog.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.gymlog.database.entities.GymLog;
import com.example.gymlog.database.entities.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GymLogRepository {
    private final GymLogDAO gymLogDAO;
    private final UserDAO userDAO;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public GymLogRepository(Application application) {
        GymLogDatabase db = GymLogDatabase.getDatabase(application);
        gymLogDAO = db.gymLogDAO();
        userDAO = db.userDAO();
    }

    // GymLog operations
    public void insert(GymLog log) {
        executor.execute(() -> gymLogDAO.insert(log));
    }

    public LiveData<List<GymLog>> getLogsForUser(int userId) {
        return gymLogDAO.getLogsForUser(userId);
    }

    // User operations
    public void insertUser(User user) {
        executor.execute(() -> userDAO.insert(user));
    }

    public LiveData<User> getUser(String username, String password) {
        return userDAO.getUser(username, password);
    }

    public LiveData<List<User>> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
