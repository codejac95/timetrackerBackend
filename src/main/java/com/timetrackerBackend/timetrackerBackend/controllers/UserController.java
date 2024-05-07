package com.timetrackerBackend.timetrackerBackend.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.timetrackerBackend.timetrackerBackend.models.User;
import com.timetrackerBackend.timetrackerBackend.services.UserService;

@CrossOrigin(origins = "*")
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

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    
}