package com.timetrackerBackend.timetrackerBackend.services;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.timetrackerBackend.timetrackerBackend.models.Task;

@Service
public class TaskService {
    
    private final MongoOperations mongoOperations;

    public TaskService(MongoOperations mongoOperations) {
        this.mongoOperations=mongoOperations;
    }

    public Task createTask(Task task) {
        return mongoOperations.save(task);
    }

    public Task startTask(String taskId) {
        Task task = getTask(taskId);
        if (task != null) {
            if (task.getStartTime() == 0 && task.getDuration() != 0) {
                long currentTime = System.currentTimeMillis();
                long elapsedTimeSinceLastStop = currentTime - task.getLastStopTime();
                task.setStartTime(currentTime - (task.getDuration() - elapsedTimeSinceLastStop));
            } else {
                task.setStartTime(System.currentTimeMillis());
            }
            mongoOperations.save(task);
        }
        return task;
    }

    public Task stopTask(String taskId) {
        Task task = getTask(taskId);
        if (task != null && task.getStartTime() != 0) {
            long currentTime = System.currentTimeMillis();
            task.setDuration(task.getDuration() + (currentTime - task.getStartTime()));
            task.setStartTime(0);
            task.setLastStopTime(currentTime); 
            mongoOperations.save(task);
        }
        return task;
    }

    private Task getTask(String taskId) {
        return mongoOperations.findById(taskId, Task.class);
    }
}