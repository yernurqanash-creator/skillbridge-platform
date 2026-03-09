package com.skillbridge.skillbridgebackend.service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skillbridge.skillbridgebackend.dto.CVUploadResponse;
import com.skillbridge.skillbridgebackend.entity.CV;
import com.skillbridge.skillbridgebackend.entity.User;
import com.skillbridge.skillbridgebackend.exception.NotFoundException;
import com.skillbridge.skillbridgebackend.repository.CVRepository;
import com.skillbridge.skillbridgebackend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CVService {

    private final CVRepository cvRepository;
    private final UserRepository userRepository;

    private static final List<String> SKILLS = List.of(
            "java", "spring", "spring boot", "python", "sql", "docker", "kubernetes", "react", "javascript",
            "typescript", "node.js", "html", "css", "git", "aws", "azure", "mongodb", "postgresql", "figma"
    );

    public CVUploadResponse processCV(Long userId, MultipartFile file) throws Exception {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User табылмады"));

        String text;
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            text = stripper.getText(document).toLowerCase();
        }

        Set<String> foundSkills = new HashSet<>();
        for (String skill : SKILLS) {
            if (text.contains(skill)) {
                foundSkills.add(skill);
            }
        }

        List<String> sortedSkills = foundSkills.stream()
                .sorted(Comparator.naturalOrder())
                .toList();

        String skills = String.join(", ", sortedSkills);
        int readinessScore = estimateReadiness(sortedSkills.size());

        CV cv = CV.builder()
                .user(user)
                .filePath(file.getOriginalFilename())
                .extractedSkills(skills)
                .readinessScore(readinessScore)
                .build();

        CV saved = cvRepository.save(cv);

        return CVUploadResponse.builder()
                .id(saved.getId())
                .extractedSkills(saved.getExtractedSkills())
                .readinessScore(saved.getReadinessScore())
                .filePath(saved.getFilePath())
                .skillsList(sortedSkills)
                .build();
    }

    private int estimateReadiness(int skillsCount) {
        if (skillsCount <= 0) {
            return 0;
        }
        return Math.min(100, skillsCount * 10);
    }
}
