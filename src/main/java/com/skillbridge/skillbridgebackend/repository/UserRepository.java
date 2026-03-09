package com.skillbridge.skillbridgebackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillbridge.skillbridgebackend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
