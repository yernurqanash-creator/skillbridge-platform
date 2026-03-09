package com.skillbridge.skillbridgebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillbridge.skillbridgebackend.entity.Internship;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
}