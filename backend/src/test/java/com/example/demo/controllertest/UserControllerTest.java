package com.example.demo.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUser() throws Exception{

        // Given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = sdf.parse("2024-06-06");
        User user = new User("Helen", "abcde123", "hardy@gmail.com", dob , "Male");

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
            .andReturn();

        // Then
        String responseBody = result.getResponse().getContentAsString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        assertEquals("Register Successfully!", jsonResponse.getString("message"));
    }

    @Test
    public void testGetProfile() throws Exception {
        int userId = 2;

        mockMvc.perform(MockMvcRequestBuilders.get("/user/get-details/{id}", userId))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userId));

    }

    @Test
    public void testUpdateProfile() throws Exception {

        // Given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = sdf.parse("2024-06-06");
            User user = new User(192, "Hardie", "abcde123", "hardy@gmail.com", dob , "Male");

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/user/update")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
            .andReturn();

        // Then
        String responseBody = result.getResponse().getContentAsString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        assertEquals("Update Successfully!", jsonResponse.getString("message"));
    }

    @Test
    public void testDeleteProfile() throws Exception {

        // Given
        int userId = 215;

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/{id}", userId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
            .andReturn();

        // Then
        String responseBody = result.getResponse().getContentAsString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        assertEquals("Delete Successfully!", jsonResponse.getString("message"));
    }

    @Test
    public void testGetAllUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/user/get-details"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
