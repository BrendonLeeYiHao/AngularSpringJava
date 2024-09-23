package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LoginRequest;

@Repository
public interface UserRequestRepository extends JpaRepository<LoginRequest, Integer> {

    @Query("SELECT u FROM LoginRequest u WHERE u.username = ?1")
    LoginRequest findByUsername(String username);
}