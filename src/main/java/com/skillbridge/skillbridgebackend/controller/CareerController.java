package com.skillbridge.skillbridgebackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.skillbridgebackend.dto.RoadmapRequest;
import com.skillbridge.skillbridgebackend.dto.RoadmapResponse;
import com.skillbridge.skillbridgebackend.service.CareerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/career")
@RequiredArgsConstructor
public class CareerController {

    private final CareerService careerService;

    @PostMapping("/roadmap")
    public RoadmapResponse roadmap(@Valid @RequestBody RoadmapRequest request) {
        return careerService.buildRoadmap(request.getInterest());
    }
}
