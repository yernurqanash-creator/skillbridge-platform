package com.skillbridge.skillbridgebackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.skillbridgebackend.dto.MatchResultDTO;
import com.skillbridge.skillbridgebackend.service.MatchingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchingService matchingService;

    @PostMapping("/run")
    public MatchResultDTO match(
            @RequestParam Long cvId,
            @RequestParam Long internshipId
    ) {
        return matchingService.match(cvId, internshipId);
    }

    @GetMapping("/history")
    public List<MatchResultDTO> history(@RequestParam Long userId) {
        return matchingService.history(userId);
    }

    @GetMapping("/recommendations")
    public List<MatchResultDTO> recommend(@RequestParam Long userId) {
        return matchingService.recommend(userId);
    }
}
