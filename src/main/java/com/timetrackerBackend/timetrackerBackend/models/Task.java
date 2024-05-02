package com.timetrackerBackend.timetrackerBackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Tasks")
public class Task {
    @Id
    private String id;
    private String taskName;
    private long startTime;
    private long duration;
    private long lastStopTime;
    
    public Task(String id, String taskName, long startTime, long duration) {
        this.id = id;
        this.taskName = taskName;
        this.startTime = startTime;
        this.duration = duration;
        this.lastStopTime = lastStopTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getLastStopTime() {
        return lastStopTime;
    }

    public void setLastStopTime(long lastStopTime) {
        this.lastStopTime = lastStopTime;
    }

    
}
