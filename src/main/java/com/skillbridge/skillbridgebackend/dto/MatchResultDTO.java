package com.skillbridge.skillbridgebackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResultDTO {
    private Long id;
    private Double matchPercent;
    private String skillGap;
    private UserDTO user;
    private InternshipDTO internship;
}