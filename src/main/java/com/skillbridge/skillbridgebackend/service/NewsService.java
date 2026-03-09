package com.skillbridge.skillbridgebackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillbridge.skillbridgebackend.dto.NewsItemDTO;

@Service
public class NewsService {

    public List<NewsItemDTO> latestNews() {
        return List.of(
                NewsItemDTO.builder()
                        .category("AI")
                        .title("AI Internship тренді өсіп жатыр")
                        .summary("Компаниялар junior AI/ML intern рөлдеріне Python+SQL талаптарын күшейтті.")
                        .url("https://www.weforum.org/stories")
                        .publishedAt("2026-03-01")
                        .build(),
                NewsItemDTO.builder()
                        .category("Backend")
                        .title("Spring және cloud stack сұранысы жоғары")
                        .summary("Backend internship вакансияларында REST, SQL, Docker негізгі skill болып тұр.")
                        .url("https://spring.io/blog")
                        .publishedAt("2026-02-26")
                        .build(),
                NewsItemDTO.builder()
                        .category("Career")
                        .title("Portfolio without fluff: hiring manager кеңесі")
                        .summary("3 нақты жоба + measurable нәтиже көрсеткен кандидаттар жоғары match алады.")
                        .url("https://www.linkedin.com")
                        .publishedAt("2026-02-20")
                        .build()
        );
    }
}
