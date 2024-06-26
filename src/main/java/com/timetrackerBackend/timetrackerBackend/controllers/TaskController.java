package com.timetrackerBackend.timetrackerBackend.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.timetrackerBackend.timetrackerBackend.models.Task;
import com.timetrackerBackend.timetrackerBackend.models.User;
import com.timetrackerBackend.timetrackerBackend.services.TaskService;
import com.timetrackerBackend.timetrackerBackend.services.UserService;


@CrossOrigin(origins = "*")
@RestController
public class TaskController {
    private TaskService taskService;
    private UserService userService; 

    public TaskController(TaskService taskService, UserService userService) { 
         this.taskService = taskService;
         this.userService = userService;
    }
    
    @GetMapping
    public String index() {
        return "index";
    }
    @GetMapping("/all-tasks")
    public List <Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/task/taskId/{id}")
    public Task getTasksById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/task/userId/{userId}")
    public List<Task> getAllTasksForUser(@PathVariable String userId) {
        return taskService.getAllTasksForUser(userId);
    }

    @PostMapping("/task/userId/{userId}")
    public Task createTask(@PathVariable String userId, @RequestBody Task task) {
        User user = userService.getUserById(userId).get(0);   
        task.setUsername(user.getUsername());
        task.setUserId(userId);
        return taskService.createTask(task);
    }
    
    @PostMapping("/task/start/{id}")
    public Task startTask(@PathVariable String id) {
       return taskService.startTaskTimer(id);
    }
    
    @PostMapping("/task/pause/{id}")
    public Task pauseTask(@PathVariable String id) {
       return taskService.pauseTaskTimer(id);
    }
    
    @DeleteMapping("/task/taskId/{id}")
    public void deleteTask(@PathVariable String id) {
     taskService.deleteTask(id);
    } 

}

