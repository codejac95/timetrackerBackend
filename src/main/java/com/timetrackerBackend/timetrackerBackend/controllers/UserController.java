package com.timetrackerBackend.timetrackerBackend.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register/{username}/{password}")
    public ResponseEntity<String> registerUser(@PathVariable String username, @PathVariable String password) {
        try {
            User existingUser = userService.getUsername(username);
            if (existingUser != null) {
                return new ResponseEntity<>("Användarnamnet är redan upptaget", HttpStatus.BAD_REQUEST);
            }
            User user = new User(username, password, false);
            userService.createUser(user);
            return new ResponseEntity<>("Användaren har registrerats", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Registreringen misslyckades", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existingUser = userService.getUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            String sessionId = existingUser.getId();
            return ResponseEntity.ok().body(Map.of("sessionId", sessionId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Felaktiga inloggningsuppgifter"));
        }
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}