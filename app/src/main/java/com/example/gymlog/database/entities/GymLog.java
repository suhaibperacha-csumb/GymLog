package com.example.gymlog.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class GymLog {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String exerciseName;
    private double weight;
    private int reps;
    private Date date;
    private int userId;

    // Constructor
    public GymLog(String exerciseName, double weight, int reps, Date date, int userId) {
        this.exerciseName = exerciseName;
        this.weight = weight;
        this.reps = reps;
        this.date = date;
        this.userId = userId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public double getWeight() {
        return weight;
    }

    public int getReps() {
        return reps;
    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return exerciseName + " - " + weight + " lbs x " + reps + " on " + date;
    }
}
