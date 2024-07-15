// package com.example.demo.daotest;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import java.text.SimpleDateFormat;
// import java.util.Date;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.example.demo.dao.UserDao;
// import com.example.demo.model.User;

// @SpringBootTest
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// public class UserDaoTest {

//     @Autowired
//     private UserDao userDao;

//     @Test
//     public void testSaveUser() throws Exception {

//         // Given
//         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//         Date dob = sdf.parse("2024-06-07");
//         User user = new User("Alexis", "aabbcc123", "AlexLee@gmail.com", dob, "Male");

//         String expectedResult = "Register Successfully!";

//         // When
//         String result = userDao.saveUser(user);

//         // Then
//         assertEquals(expectedResult, result);
//     }

//     @Test
//     public void testGetUser() throws Exception {

//         // Given
//         int userId = 11;

//         // When
//         User user = userDao.getProfileById(userId);
//         assert(user != null);
//     }

//     @Test
//     public void testUpdateUser() throws Exception {

//         // Given
//         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//         Date dob = sdf.parse("2024-06-06");
//         User user = new User(196, "Helen", "aaabbbccc", "Helen@gmail.com", dob, "Female");

//         String expectedResult = "Update Successfully!";

//         // When
//         User retrievedUser = userDao.getProfileById(user.getId());
//         assert(retrievedUser != null);
//         String result = userDao.updateProfile(user);

//         // Then
//         assertEquals(expectedResult, result);

//         User updatedUser = userDao.getProfileById(user.getId());
//         assertEquals(user.getPassword(), updatedUser.getPassword());
//     }

//     @Test
//     public void testDeleteUser() throws Exception {

//         // Given
//         int userId = 7;
//         String expectedResult = "Delete Successfully!";

//         // When
//         User user = userDao.getProfileById(userId);
//         assert(user != null);

//         String result = userDao.deleteUser(userId);

//         // Then
//         assertEquals(expectedResult, result);
//     }
// }
