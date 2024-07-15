package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

    public String createUser(User user);
    public User getProfileById(Integer id);
    public String updateProfile(User user);
    public String deleteUser(Integer id);
    public List<User> getAllUser();
}
