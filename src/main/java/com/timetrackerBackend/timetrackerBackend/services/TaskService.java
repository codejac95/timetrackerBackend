package com.timetrackerBackend.timetrackerBackend.services;

import java.util.List;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.timetrackerBackend.timetrackerBackend.models.Task;

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

    public List <Task> getTaskById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoOperations.find(query, Task.class);
    }
        
    public Task createTask(Task task) {
        return mongoOperations.save(task);
    }

    public void deleteTask(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        mongoOperations.remove(query,Task.class);
    }
}
  