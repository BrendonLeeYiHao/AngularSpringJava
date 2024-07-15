// package com.example.demo.servicetest;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.when;

// import java.text.SimpleDateFormat;
// import java.util.Date;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.example.demo.dao.UserDao;
// import com.example.demo.model.User;
// import com.example.demo.service.UserServiceImpl;

// @ExtendWith(MockitoExtension.class)
// public class UserServiceTest2 {

//     @InjectMocks    // To specify the class you want to test
//     private UserServiceImpl userService;

//     @Mock
//     private UserDao userDao;

//     @Test
//     public void testCreateUser() throws Exception {
//         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//         Date dob = sdf.parse("2024-06-06");
//         User user = new User("Alexis", "aabbcc123", "AlexLee@gmail.com", dob, "Male");

//         String expectedResult = "Register Successfully!";

//         when(userDao.saveUser(user)).thenReturn(expectedResult);

//         // When
//         String result = userService.createUser(user);

//         // Then
//         assertEquals(expectedResult, result);
//     }

//     @Test
//     public void testUpdateUser() throws Exception {
//         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//         Date dob = sdf.parse("2024-06-06");
//         User user = new User(196, "Helen", "abcba123", "Helen@gmail.com", dob, "Female");

//         String expectedResult = "Update Successfully!";

//         when(userDao.updateProfile(user)).thenReturn(expectedResult);

//         // When
//         String result = userService.updateProfile(user);

//         // Then
//         assertEquals(expectedResult, result);
//     }

//     @Test
//     public void testGetUser() throws Exception {
        
//         // Given
//         int userId = 1;
//         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//         Date dob = sdf.parse("2024-07-05");
//         User expectedResult = new User(userId, "Tam", "abc123", "tam@gmail.com", dob, "Male");

//         when(userDao.getProfileById(userId)).thenReturn(expectedResult);

//         // When
//         User result = userService.getProfileById(userId);

//         // Then
//         assertEquals(expectedResult, result);
//     }

//     @Test
//     public void testDeleteUser() throws Exception {

//         // Given
//         int userId = 194;
//         String expectedResult = "Delete Successfully!";

//         when(userDao.deleteUser(userId)).thenReturn(expectedResult);

//         // When
//         String result = userService.deleteUser(userId);

//         // Then
//         assertEquals(expectedResult, result);
//     }
// }
