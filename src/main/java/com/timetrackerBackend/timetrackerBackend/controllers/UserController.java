package com.timetrackerBackend.timetrackerBackend.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.timetrackerBackend.timetrackerBackend.models.User;
import com.timetrackerBackend.timetrackerBackend.services.UserService;

@RestController
public class UserController {
    
    private UserService userService;

    public UserController (UserService userService){
         this.userService=userService;
    }
 
    @GetMapping("/user")
    public List <User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public List <User> getUserByid(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

}