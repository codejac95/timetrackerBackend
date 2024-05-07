package com.timetrackerBackend.timetrackerBackend.services;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.timetrackerBackend.timetrackerBackend.models.Task;

import jakarta.annotation.PostConstruct;

@Service
public class TaskService {
    
    private final MongoOperations mongoOperations;

    public TaskService(MongoOperations mongoOperations) {
        this.mongoOperations=mongoOperations;
    }

    public List <Task> getAllTasks(){
        return mongoOperations.findAll(Task.class);
    }

    public List <Task> getAllTasksForUser(String userId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        return mongoOperations.find(query, Task.class);
    }

    public Task getTaskById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoOperations.findOne(query, Task.class);
    }
        
    public Task createTask(Task task) {
        return mongoOperations.save(task);
    }

    public Task startTaskTimer(String id) {
        Task task = getTaskById(id);
        task.startTimer();
        return mongoOperations.save(task);
    }

    public Task pauseTaskTimer(String id) {
        Task task = getTaskById(id);
        task.pauseTimer();
        return mongoOperations.save(task);
    }

    public void deleteTask(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoOperations.remove(query, Task.class);
    }

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    @PostConstruct
    public void startTimerUpdateTask() {
        executorService.scheduleAtFixedRate(this::updateTimers, 0, 1, TimeUnit.MINUTES);
    }

    private void updateTimers() {
        List<Task> tasks = getAllTasks();
        for (Task task : tasks) {
            task.updateTimer();
            mongoOperations.save(task);
        }
    }
    
}


  