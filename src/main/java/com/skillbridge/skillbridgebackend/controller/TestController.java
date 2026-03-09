package com.skillbridge.skillbridgebackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.skillbridgebackend.dto.AuthResponse;
import com.skillbridge.skillbridgebackend.dto.InternshipDTO;
import com.skillbridge.skillbridgebackend.dto.LoginRequest;
import com.skillbridge.skillbridgebackend.dto.MatchResultDTO;
import com.skillbridge.skillbridgebackend.dto.RegisterRequest;
import com.skillbridge.skillbridgebackend.entity.Internship;
import com.skillbridge.skillbridgebackend.repository.InternshipRepository;
import com.skillbridge.skillbridgebackend.service.AuthService;
import com.skillbridge.skillbridgebackend.service.MatchingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final AuthService authService;
    private final InternshipRepository internshipRepository;
    private final MatchingService matchingService;

    @PostMapping("/create-user")
    public AuthResponse createUser(@RequestBody(required = false) RegisterRequest dto) {
        RegisterRequest request = dto == null ? new RegisterRequest() : dto;

        if (request.getName() == null || request.getName().isBlank()) {
            request.setName("Demo User");
        }
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            request.setEmail("demo" + System.currentTimeMillis() + "@skillbridge.kz");
        }
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            request.setPassword("123456");
        }

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest dto) {
        return authService.login(dto);
    }

    @PostMapping("/create-internship")
    public Internship createInternship(@Valid @RequestBody InternshipDTO dto) {

        Internship internship = Internship.builder()
                .title(dto.getTitle())
                .companyName(dto.getCompanyName())
                .description(dto.getDescription())
                .requiredSkills(dto.getRequiredSkills())
                .build();

        return internshipRepository.save(internship);
    }

    @PostMapping("/match")
    public MatchResultDTO match(
            @RequestParam Long cvId,
            @RequestParam Long internshipId
    ) {
        return matchingService.match(cvId, internshipId);
    }

    @GetMapping("/match-history")
    public List<MatchResultDTO> getMatchHistory(@RequestParam Long userId) {
        return matchingService.history(userId);
    }

    @GetMapping("/recommend")
    public List<MatchResultDTO> recommend(@RequestParam Long userId) {
        return matchingService.recommend(userId);
    }
}
