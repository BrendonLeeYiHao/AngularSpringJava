package com.example.demo.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.AgeNotEighteenException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
// import com.example.demo.response.AuthResponse;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

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
        if (existingUser == null) {
            throw new UserNotFoundException("User with ID " + id + " not found.");     
        }
        userRepository.deleteById(id);
        return "Delete Successfully!";
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


    // With DTO (Currently In Use)
    @Override
    public String createUser(UserDTO userDTO) {
        User existingUser = userRepository.findByName(userDTO.getName());
        if(existingUser != null) {
            throw new UserAlreadyExistsException("unique_name_msg");
        }
        if(isValidDob(userDTO.getDob())) {
            userRepository.save(convertToEntity(userDTO));
        } else {
            throw new AgeNotEighteenException("age_not_eighteen_msg");
        }
        return "Register Successfully!";
    } 

    private boolean isValidDob(Date dob) {
        LocalDate dobLocalDate = new java.sql.Date(dob.getTime()).toLocalDate();
        LocalDate now = LocalDate.now();
        int age = Period.between(dobLocalDate, now).getYears();
        return age >= 18;
    }

    @Override
    public List<UserDTO> getAllUserDTO() {
        Sort sortById = Sort.by("id").ascending();
        List<User> users = userRepository.findAll(sortById);
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String updateProfile(UserDTO userDTO) {
        User existingUser = getProfileById(userDTO.getId());
        if (existingUser == null) {
            throw new UserNotFoundException("User with ID " + userDTO.getId() + " not found.");
        }
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setEmail(userDTO.getEmail());
        userRepository.save(existingUser);
        return "Update Successfully!";
    }

    @Override
    public Map<String, String> login(UserDTO userDTO) {
        User retrievedUser = userRepository.findByName(userDTO.getName());
        if (retrievedUser != null) {
            if (userDTO.getPassword().equals(retrievedUser.getPassword())) {
                String token = jwtService.generateToken(userDTO.getName());
                Map<String, String> userMap = new HashMap<>();
                userMap.put("user", retrievedUser.getName());
                userMap.put("token", token);
                return userMap;
            }
            // if (checkPassword(user.getPassword(), retrievedUser.getPassword())) {
            //     return "true";
            // }
        }
        throw new BadCredentialsException("Invalid username or password");
    }
}