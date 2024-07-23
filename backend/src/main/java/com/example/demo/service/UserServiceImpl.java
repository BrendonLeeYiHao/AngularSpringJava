package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String createUser(User user) {
        // String encodedPassword = passwordEncoder.encode(user.getPassword());
        // user.setPassword(encodedPassword);
        userRepository.save(user);
        return "Register Successfully!";
    }

    @Override
    public User getProfileById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public String updateProfile(User user) {
        User existingUser = getProfileById(user.getId());
        if (existingUser != null) {
            userRepository.save(user);
            return "Update Successfully!";
        }
        return null;
    }

    @Override
    public String deleteUser(Integer id) {
        User existingUser = getProfileById(id);
        if (existingUser != null) {
            userRepository.deleteById(id);
            return "Delete Successfully!";
        }
        return null;
    }

    @Override
    public List<User> getAllUser() {
        Sort sortById = Sort.by("id").ascending();
        return userRepository.findAll(sortById);
    }

    @Override
    public String login(User user) {
        User retrievedUser = userRepository.findByName(user.getName());
        if (retrievedUser != null) {
            if (user.getPassword().equals(retrievedUser.getPassword())) {
                return "true";
            }
            // if (checkPassword(user.getPassword(), retrievedUser.getPassword())) {
            //     return "true";
            // }
        }
        return "false";
    }

    public boolean checkPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}

