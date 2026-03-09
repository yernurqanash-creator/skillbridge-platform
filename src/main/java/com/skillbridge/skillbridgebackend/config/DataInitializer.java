package com.skillbridge.skillbridgebackend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.skillbridge.skillbridgebackend.entity.Internship;
import com.skillbridge.skillbridgebackend.repository.InternshipRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedInternships(InternshipRepository internshipRepository) {
        return args -> {
            if (internshipRepository.count() > 0) {
                return;
            }

            internshipRepository.save(Internship.builder()
                    .title("Java Backend Intern")
                    .companyName("TechCorp")
                    .description("REST API development with Spring")
                    .requiredSkills("java, spring, sql, git")
                    .build());

            internshipRepository.save(Internship.builder()
                    .title("Frontend Intern")
                    .companyName("UI Labs")
                    .description("Web UI development with React")
                    .requiredSkills("javascript, react, html, css, figma")
                    .build());

            internshipRepository.save(Internship.builder()
                    .title("Data Analyst Intern")
                    .companyName("Insight Hub")
                    .description("Data cleaning and dashboard reporting")
                    .requiredSkills("python, sql, excel, power bi")
                    .build());
        };
    }
}
