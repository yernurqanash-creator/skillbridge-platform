package com.skillbridge.skillbridgebackend.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.skillbridge.skillbridgebackend.dto.InternshipDTO;
import com.skillbridge.skillbridgebackend.dto.MatchResultDTO;
import com.skillbridge.skillbridgebackend.dto.UserDTO;
import com.skillbridge.skillbridgebackend.entity.CV;
import com.skillbridge.skillbridgebackend.entity.Internship;
import com.skillbridge.skillbridgebackend.entity.MatchResult;
import com.skillbridge.skillbridgebackend.entity.User;
import com.skillbridge.skillbridgebackend.exception.BadRequestException;
import com.skillbridge.skillbridgebackend.exception.NotFoundException;
import com.skillbridge.skillbridgebackend.repository.CVRepository;
import com.skillbridge.skillbridgebackend.repository.InternshipRepository;
import com.skillbridge.skillbridgebackend.repository.MatchResultRepository;
import com.skillbridge.skillbridgebackend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchingService {

    private final CVRepository cvRepository;
    private final InternshipRepository internshipRepository;
    private final MatchResultRepository matchResultRepository;
    private final UserRepository userRepository;

    public MatchResultDTO match(Long cvId, Long internshipId) {

        CV cv = cvRepository.findById(cvId)
                .orElseThrow(() -> new NotFoundException("CV табылмады"));

        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new NotFoundException("Internship табылмады"));

        if (cv.getExtractedSkills() == null || cv.getExtractedSkills().isBlank()) {
            throw new BadRequestException("CV ішінде skills жоқ");
        }

        if (internship.getRequiredSkills() == null || internship.getRequiredSkills().isBlank()) {
            throw new BadRequestException("Internship required skills жоқ");
        }

        Set<String> cvSkills = parseSkills(cv.getExtractedSkills());
        Set<String> requiredSkills = parseSkills(internship.getRequiredSkills());

        Set<String> matched = new HashSet<>(cvSkills);
        matched.retainAll(requiredSkills);

        double percent = requiredSkills.isEmpty()
                ? 0
                : ((double) matched.size() / requiredSkills.size()) * 100;

        Set<String> gap = new HashSet<>(requiredSkills);
        gap.removeAll(cvSkills);

        MatchResult result = MatchResult.builder()
                .matchPercent(percent)
                .skillGap(String.join(", ", gap.stream().sorted().toList()))
                .user(cv.getUser())
                .internship(internship)
                .build();

        MatchResult saved = matchResultRepository.save(result);
        return toDto(saved);
    }

    public List<MatchResultDTO> recommend(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User табылмады"));

        CV cv = cvRepository.findTopByUserOrderByIdDesc(user)
                .orElseThrow(() -> new NotFoundException("Алдымен CV жүктеу керек"));

        if (cv.getExtractedSkills() == null || cv.getExtractedSkills().isBlank()) {
            throw new BadRequestException("CV ішінде skills жоқ");
        }

        List<Internship> internships = internshipRepository.findAll();

        return internships.stream()
                .map(internship -> calculateOnly(cv, internship))
                .sorted((a, b) -> Double.compare(b.getMatchPercent(), a.getMatchPercent()))
                .toList();
    }

    public List<MatchResultDTO> history(Long userId) {
        return matchResultRepository.findByUser_Id(userId)
                .stream()
                .sorted((a, b) -> Long.compare(b.getId(), a.getId()))
                .map(this::toDto)
                .toList();
    }

    private MatchResultDTO calculateOnly(CV cv, Internship internship) {
        Set<String> cvSkills = parseSkills(cv.getExtractedSkills());
        Set<String> requiredSkills = parseSkills(internship.getRequiredSkills());

        Set<String> matched = new HashSet<>(cvSkills);
        matched.retainAll(requiredSkills);

        double percent = requiredSkills.isEmpty()
                ? 0
                : ((double) matched.size() / requiredSkills.size()) * 100;

        Set<String> gap = new HashSet<>(requiredSkills);
        gap.removeAll(cvSkills);

        return MatchResultDTO.builder()
                .id(null)
                .matchPercent(percent)
                .skillGap(String.join(", ", gap.stream().sorted().toList()))
                .user(UserDTO.builder()
                        .id(cv.getUser().getId())
                        .name(cv.getUser().getName())
                        .email(cv.getUser().getEmail())
                        .build())
                .internship(InternshipDTO.builder()
                        .id(internship.getId())
                        .title(internship.getTitle())
                        .companyName(internship.getCompanyName())
                        .description(internship.getDescription())
                        .requiredSkills(internship.getRequiredSkills())
                        .build())
                .build();
    }

    private MatchResultDTO toDto(MatchResult saved) {
        return MatchResultDTO.builder()
                .id(saved.getId())
                .matchPercent(saved.getMatchPercent())
                .skillGap(saved.getSkillGap())
                .user(UserDTO.builder()
                        .id(saved.getUser().getId())
                        .name(saved.getUser().getName())
                        .email(saved.getUser().getEmail())
                        .build())
                .internship(InternshipDTO.builder()
                        .id(saved.getInternship().getId())
                        .title(saved.getInternship().getTitle())
                        .companyName(saved.getInternship().getCompanyName())
                        .description(saved.getInternship().getDescription())
                        .requiredSkills(saved.getInternship().getRequiredSkills())
                        .build())
                .build();
    }

    private Set<String> parseSkills(String skills) {
        if (skills == null || skills.isBlank()) {
            return new HashSet<>();
        }

        return Arrays.stream(skills.split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(s -> !s.isBlank())
                .collect(Collectors.toSet());
    }
}
