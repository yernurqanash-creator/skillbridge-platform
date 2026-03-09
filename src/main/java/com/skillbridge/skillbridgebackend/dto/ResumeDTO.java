package com.skillbridge.skillbridgebackend.dto;

import lombok.Data;

@Data
public class ResumeDTO {

    private String name;
    private String email;
    private String university;
    private String skills;
    private String projects;
    private String languages;

}