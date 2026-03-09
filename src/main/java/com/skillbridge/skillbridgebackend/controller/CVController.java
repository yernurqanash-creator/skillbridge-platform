package com.skillbridge.skillbridgebackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skillbridge.skillbridgebackend.dto.CVUploadResponse;
import com.skillbridge.skillbridgebackend.service.CVService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cv")
@RequiredArgsConstructor
public class CVController {

    private final CVService cvService;

    @PostMapping("/upload")
    public CVUploadResponse uploadCV(
            @RequestParam Long userId,
            @RequestParam MultipartFile file
    ) throws Exception {

        return cvService.processCV(userId, file);
    }
}
