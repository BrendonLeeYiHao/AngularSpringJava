package com.example.demo.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testCreateUser() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = sdf.parse("2024-06-06");
        User user = new User("Minji", "aabbcc123", "minji@gmail.com", dob, "Female");

        String expectedResult = "Register Successfully!";

        when(userRepository.save(user)).thenReturn(user);

        // When
        String result = userService.createUser(user);

        // Then
        assertEquals(expectedResult, result);
        verify(userRepository).save(user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = sdf.parse("2024-06-06");
        User user = new User(196, "Helen", "abcba123", "Helen@gmail.com", dob, "Female");

        String expectedResult = "Update Successfully!";

        // Simulate Existing User
        User existingUser = new User(196, "Helen", "abcba12345", "Helen123@gmail.com", dob, "Female");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(user)).thenReturn(user);

        // When
        String result = userService.updateProfile(user);

        // Then
        assertEquals(expectedResult, result);
        verify(userRepository).findById(user.getId());
        verify(userRepository).save(user);
    }

    @Test
    public void testGetUser() throws Exception {
        
        // Given
        int userId = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = sdf.parse("2024-07-05");
        User expectedResult = new User(userId, "Tam", "abc123", "tam@gmail.com", dob, "Male");

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedResult));

        // When
        User result = userService.getProfileById(userId);

        // Then
        assertEquals(expectedResult, result);
        verify(userRepository).findById(userId);
    }

    @Test
    public void testDeleteUser() throws Exception {

        // Given
        int userId = 210;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = sdf.parse("2006-06-27");
        User user = new User(userId, "Yoyo", "abc123", "yoyo@gmail.com", dob, "Male");
        String expectedResult = "Delete Successfully!";

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        String result = userService.deleteUser(userId);

        // Then
        assertEquals(expectedResult, result);
        verify(userRepository).findById(userId);
        verify(userRepository).deleteById(userId);
    }

    @Test
    public void testGetAllUser() throws Exception {
        Sort sortById = Sort.by("id").ascending();
        // Given
        List<User> expectedUsers = Arrays.asList(
            new User(5, "Desmond", "abc123", "desmondie@gmail.com", new SimpleDateFormat("yyyy-MM-dd").parse("2024-06-03"), "Male"),
            new User(6, "Desmondie", "abc123123", "desmondie@gmail.com", new SimpleDateFormat("yyyy-MM-dd").parse("2024-06-08"), "Male")
        );

        when(userRepository.findAll(sortById)).thenReturn(expectedUsers);

        // When
        List<User> result = userService.getAllUser();

        // Then
        assertEquals(expectedUsers, result);
        verify(userRepository).findAll(sortById);
    }
}
