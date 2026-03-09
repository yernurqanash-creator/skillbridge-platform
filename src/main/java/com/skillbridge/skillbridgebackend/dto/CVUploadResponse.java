package com.skillbridge.skillbridgebackend.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CVUploadResponse {
    private Long id;
    private String extractedSkills;
    private Integer readinessScore;
    private String filePath;
    private List<String> skillsList;
}
