package com.timetrackerBackend.timetrackerBackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Tasks")
public class Task {
    @Id
    private String id;
    private String name;
    private String username;
    private long totalTime;
    private long startTime; 
    private boolean timerRunning;
    private String userId;
    
    
    public Task(String name, String userId, String username) {
        this.name = name;
        this.userId = userId;
        this.username = username;
        this.totalTime = 0;
        this.startTime = 0;
        this.timerRunning = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }  

    public void startTimer() {
        if (!timerRunning) {
            startTime = System.currentTimeMillis()/1000;
            timerRunning = true;
        }
    }

    public void pauseTimer() {
        if (timerRunning) {
            long currentTime = System.currentTimeMillis()/1000;
            totalTime += (currentTime - startTime);
            startTime = 0; 
            timerRunning = false; 
        }
    }

    public boolean isTimerRunning() {
        return timerRunning;
    }

    public void setTimerRunning(boolean timerRunning) {
        this.timerRunning = timerRunning;
    }
}
