package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.response.SuccessResponse;
import com.example.demo.service.JWTService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired 
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    private final Map<String, String> responseMap = new HashMap<>();

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> createUser(@RequestBody User user) {
        String response = userService.createUser(user);
        responseMap.put("message", response);
        return ResponseEntity.ok().body(responseMap);
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

    @GetMapping(value = "/get-details")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok().body(allUser);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        String response = userService.login(user);
        responseMap.put("message", response);
        return ResponseEntity.ok().body(responseMap);
    }


    // With DTO
    @PostMapping(value = "/register-dto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<Void>> createUserDTO(@Valid @RequestBody UserDTO userDTO) {
        String response = userService.createUser(userDTO);
        return ResponseEntity.ok(new SuccessResponse<>(null, response));
    }

    @GetMapping(value = "/get-details-dto")
    public ResponseEntity<SuccessResponse<List<UserDTO>>> getAllUserDTO() {
        List<UserDTO> allUser = userService.getAllUserDTO();
        return ResponseEntity.ok(new SuccessResponse<>(allUser, "Success"));
    }

    @PutMapping(value = "/update-dto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<Void>> updateProfileDTO(@Valid @RequestBody UserDTO userDTO) {
        String response = userService.updateProfile(userDTO);
        return ResponseEntity.ok(new SuccessResponse<>(null, response));
    }

    @PostMapping(value = "/login-dto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<Map<String, String>>> login(@RequestBody UserDTO userDTO) {
        Map<String, String> response = userService.login(userDTO);
        return ResponseEntity.ok(new SuccessResponse<>(response, "Success"));
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<Void>> deleteProfile(@PathVariable("id") Integer id) {
        String response = userService.deleteUser(id);
        return ResponseEntity.ok(new SuccessResponse<>(null, response));
    }

    @PostMapping(value = "/validate-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<Boolean>> validateToken(@RequestParam(value = "token") String token) {
        return ResponseEntity.ok(new SuccessResponse<>(jwtService.validateToken(token), "Success"));
    }
}