package com.skillbridge.skillbridgebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillbridge.skillbridgebackend.entity.MatchResult;

public interface MatchResultRepository extends JpaRepository<MatchResult, Long> {

    List<MatchResult> findByUser_Id(Long userId);

}