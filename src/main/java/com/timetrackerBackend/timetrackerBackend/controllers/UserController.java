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
public class UserController {
    
    private UserService userService;
    private TaskService taskService;

    public UserController (UserService userService, TaskService taskService){
         this.userService=userService;
         this.taskService=taskService;
    }

    @GetMapping("/user")
    public List <User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public List <User> getUserByid(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return userService.registerUser(user.getUsername(), user.getPassword());
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        User existingUser = userService.getUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return existingUser;
        } else {
            return null;
        }
    }

    @GetMapping("/logout/{userId}")
    public void logoutUser(@PathVariable String userId) {
        List<Task> userTasks = taskService.getAllTasksForUser(userId);
        for(Task task : userTasks){
            if(task.isTimerRunning()) {
                task.pauseTimer();
                taskService.updateTask(task); 
            } 
        }
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserAndTasks(@PathVariable String id) {
        List<Task> userTasks = taskService.getAllTasksForUser(id);
        for(Task task:userTasks){
            taskService.deleteTask(task.getId());
        }
        userService.deleteUser(id);
    } 
}