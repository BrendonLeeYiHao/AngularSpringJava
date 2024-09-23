package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;

public interface UserService {

    public String createUser(User user);
    public User getProfileById(Integer id);
    public String updateProfile(User user);
    public String deleteUser(Integer id);
    public List<User> getAllUser();
    public String login(User user);


    // With DTO
    public String createUser(UserDTO userDTO);
    public List<UserDTO> getAllUserDTO();
    public String updateProfile(UserDTO userDTO);
    public Map<String, String> login(UserDTO userDTO);
}