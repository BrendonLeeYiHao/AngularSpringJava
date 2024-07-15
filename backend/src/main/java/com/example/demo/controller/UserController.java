package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired 
    private UserService userService;

    private final Map<String, String> responseMap = new HashMap<>();

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> createUser(@RequestBody User user) {
        String response = userService.createUser(user);
        responseMap.put("message", response);
        return ResponseEntity.ok().body(responseMap);
        // return ResponseEntity.ok().body("{\"message\": \"" + response + "\"}");
    }

    @GetMapping("/get-details/{id}")
    public ResponseEntity<User> getProfile(@PathVariable("id") Integer id) {
        User userProfile = userService.getProfileById(id);
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> updateProfile(@RequestBody User user) {
        String response = userService.updateProfile(user);
        responseMap.put("message", response);
        return ResponseEntity.ok().body(responseMap);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProfile(@PathVariable("id") Integer id) {
        String response = userService.deleteUser(id);
        return ResponseEntity.ok().body("{\"message\": \"" + response + "\"}");
    }

    @GetMapping("/get-details")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok().body(allUser);
    }
}
