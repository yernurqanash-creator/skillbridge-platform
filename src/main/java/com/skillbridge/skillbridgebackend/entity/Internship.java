package com.skillbridge.skillbridgebackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "internships")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String companyName;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Мысалы: "java, spring, sql"
    @Column(columnDefinition = "TEXT")
    private String requiredSkills;

}