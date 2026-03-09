package com.skillbridge.skillbridgebackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.skillbridgebackend.dto.InternshipDTO;
import com.skillbridge.skillbridgebackend.entity.Internship;
import com.skillbridge.skillbridgebackend.repository.InternshipRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipRepository internshipRepository;

    @GetMapping
    public List<Internship> list() {
        return internshipRepository.findAll();
    }

    @PostMapping
    public Internship create(@Valid @RequestBody InternshipDTO dto) {
        Internship internship = Internship.builder()
                .title(dto.getTitle())
                .companyName(dto.getCompanyName())
                .description(dto.getDescription())
                .requiredSkills(dto.getRequiredSkills())
                .build();

        return internshipRepository.save(internship);
    }
}
