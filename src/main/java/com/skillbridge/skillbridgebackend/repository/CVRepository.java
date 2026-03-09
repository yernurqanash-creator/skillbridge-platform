package com.skillbridge.skillbridgebackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillbridge.skillbridgebackend.entity.CV;
import com.skillbridge.skillbridgebackend.entity.User;

public interface CVRepository extends JpaRepository<CV, Long> {

    List<CV> findByUser(User user);

    Optional<CV> findTopByUserOrderByIdDesc(User user);
}
