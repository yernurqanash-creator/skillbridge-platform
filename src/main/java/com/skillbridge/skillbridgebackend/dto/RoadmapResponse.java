package com.skillbridge.skillbridgebackend.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoadmapResponse {
    private String track;
    private List<String> month1;
    private List<String> month2;
    private List<String> month3;
    private List<String> recommendations;
}
