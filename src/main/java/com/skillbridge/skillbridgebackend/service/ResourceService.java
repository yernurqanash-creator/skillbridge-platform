package com.skillbridge.skillbridgebackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillbridge.skillbridgebackend.dto.ResourceItemDTO;

@Service
public class ResourceService {

    public List<ResourceItemDTO> resources() {
        return List.of(
                ResourceItemDTO.builder()
                        .title("LeetCode 75")
                        .type("Practice")
                        .description("Интервьюге дайындық үшін базалық алгоритмдік set.")
                        .link("https://leetcode.com/studyplan/leetcode-75/")
                        .build(),
                ResourceItemDTO.builder()
                        .title("Roadmap.sh")
                        .type("Roadmap")
                        .description("Backend, frontend және data бағыттары бойынша интерактив roadmap.")
                        .link("https://roadmap.sh")
                        .build(),
                ResourceItemDTO.builder()
                        .title("CV Checklist")
                        .type("Career")
                        .description("ATS-friendly резюме үшін қысқа чеклист.")
                        .link("https://www.indeed.com/career-advice/resumes-cover-letters")
                        .build()
        );
    }
}
