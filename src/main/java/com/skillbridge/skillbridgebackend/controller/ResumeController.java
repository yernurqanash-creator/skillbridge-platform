package com.skillbridge.skillbridgebackend.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.skillbridgebackend.dto.ResumeDTO;
import com.skillbridge.skillbridgebackend.service.PdfService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final PdfService pdfService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generate(@RequestBody ResumeDTO dto) throws Exception {

        byte[] pdf = pdfService.generateResume(dto);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=resume.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}