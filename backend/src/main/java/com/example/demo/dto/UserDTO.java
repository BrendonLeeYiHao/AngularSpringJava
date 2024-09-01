package com.example.demo.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserDTO {

    private Integer id;

    @NotBlank(message = "name_required_msg")
    @Pattern(regexp = "^(?!.*[^a-zA-Z ])(?=[A-Z].*)(?!.* {2}.*)(?!.*[A-Z]{2}.*)(?!.*[a-z][A-Z].*)(?!.*[A-Z] .*)(?!.* [a-z].*).{2,79}[a-z]$", message = "name_invalid_pattern_msg")
    private String name;

    @NotBlank(message = "password_required_msg")
    private String password;

    @Email(message = "email_invalid_msg")
    @NotBlank(message = "email_required_msg")
    private String email;

    @NotNull(message = "dob_required_msg")
    private Date dob;

    @NotBlank(message = "gender_required_msg")
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO() {}

    public UserDTO(Integer id, String name, String password, String email, Date dob, String gender) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
    }

    public UserDTO(String name, String password, String email, Date dob, String gender) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
    }
}