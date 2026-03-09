package com.skillbridge.skillbridgebackend.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import com.skillbridge.skillbridgebackend.dto.ResumeDTO;

@Service
public class PdfService {

    private static final PDFont FONT_REGULAR = PDType1Font.HELVETICA;
    private static final PDFont FONT_BOLD = PDType1Font.HELVETICA_BOLD;

    public byte[] generateResume(ResumeDTO dto) throws Exception {
        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDRectangle box = page.getMediaBox();
            float pageWidth = box.getWidth();
            float pageHeight = box.getHeight();

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                drawBackground(content, pageWidth, pageHeight);
                drawHeader(content, dto, pageWidth, pageHeight);
                drawBody(content, dto, pageWidth, pageHeight);
                drawBottomBar(content, pageWidth);
            }

            document.save(out);
            return out.toByteArray();
        }
    }

    private void drawBackground(PDPageContentStream content, float pageWidth, float pageHeight) throws Exception {
        content.setNonStrokingColor(new Color(245, 248, 255));
        content.addRect(0, 0, pageWidth, pageHeight);
        content.fill();

        content.setNonStrokingColor(new Color(228, 237, 255));
        content.addRect(0, pageHeight - 190, pageWidth, 190);
        content.fill();

        content.setNonStrokingColor(new Color(234, 244, 255));
        content.addRect(0, 0, pageWidth, 48);
        content.fill();
    }

    private void drawHeader(PDPageContentStream content, ResumeDTO dto, float pageWidth, float pageHeight) throws Exception {
        float margin = 34;
        float headerY = pageHeight - 176;
        float headerH = 132;

        content.setNonStrokingColor(new Color(20, 43, 105));
        content.addRect(margin, headerY, pageWidth - margin * 2, headerH);
        content.fill();

        String name = safe(dto.getName(), "Candidate Name");
        String email = safe(dto.getEmail(), "email@example.com");
        String university = safe(dto.getUniversity(), "University");

        writeText(content, name, FONT_BOLD, 31, margin + 20, pageHeight - 88, Color.WHITE);
        writeText(content, "Internship-ready AI/Engineering candidate", FONT_REGULAR, 12, margin + 20, pageHeight - 112, new Color(194, 216, 255));
        writeText(content, email + " | " + university, FONT_REGULAR, 11, margin + 20, pageHeight - 131, new Color(222, 233, 255));

        drawChip(content, margin + 20, pageHeight - 160, "ATS READY", new Color(63, 141, 255));
        drawChip(content, margin + 104, pageHeight - 160, "HIGH SIGNAL", new Color(104, 110, 255));
        drawChip(content, margin + 198, pageHeight - 160, "FAST LEARNER", new Color(126, 88, 255));

        content.setNonStrokingColor(new Color(65, 151, 255));
        content.addRect(pageWidth - margin - 124, pageHeight - 137, 102, 34);
        content.fill();
        writeText(content, "TOP PICK", FONT_BOLD, 10, pageWidth - margin - 102, pageHeight - 124, Color.WHITE);
    }

    private void drawBody(PDPageContentStream content, ResumeDTO dto, float pageWidth, float pageHeight) throws Exception {
        float margin = 34;
        float yTop = pageHeight - 196;
        float yBottom = 56;
        float gap = 14;

        float leftW = 200;
        float rightW = pageWidth - margin * 2 - leftW - gap;
        float leftX = margin;
        float rightX = leftX + leftW + gap;
        float cardH = yTop - yBottom;

        List<String> skills = splitItems(dto.getSkills(), 11);
        List<String> languages = splitItems(dto.getLanguages(), 8);
        List<String> projects = splitProjects(dto.getProjects());

        drawLeftPanel(content, leftX, yBottom, leftW, cardH, skills, languages);
        drawRightPanel(content, rightX, yBottom, rightW, cardH, dto, skills, projects);
    }

    private void drawLeftPanel(PDPageContentStream content,
                               float x,
                               float y,
                               float w,
                               float h,
                               List<String> skills,
                               List<String> languages) throws Exception {

        fillCard(content, x, y, w, h, new Color(229, 238, 255));

        float p = 14;
        float cursorY = y + h - 22;

        writeText(content, "CORE SKILLS", FONT_BOLD, 11, x + p, cursorY, new Color(21, 43, 97));
        cursorY -= 22;

        if (skills.isEmpty()) {
            drawBadge(content, x + p, cursorY, w - p * 2, "No skills provided", new Color(216, 228, 250), new Color(41, 64, 118));
            cursorY -= 24;
        } else {
            for (String skill : skills) {
                cursorY = drawBadge(content, x + p, cursorY, w - p * 2, skill, new Color(216, 228, 250), new Color(41, 64, 118));
            }
        }

        cursorY -= 6;
        writeText(content, "LANGUAGES", FONT_BOLD, 11, x + p, cursorY, new Color(21, 43, 97));
        cursorY -= 22;

        if (languages.isEmpty()) {
            drawBadge(content, x + p, cursorY, w - p * 2, "Not specified", new Color(219, 241, 255), new Color(26, 86, 124));
        } else {
            for (String lang : languages) {
                cursorY = drawBadge(content, x + p, cursorY, w - p * 2, lang, new Color(219, 241, 255), new Color(26, 86, 124));
            }
        }
    }

    private void drawRightPanel(PDPageContentStream content,
                                float x,
                                float y,
                                float w,
                                float h,
                                ResumeDTO dto,
                                List<String> skills,
                                List<String> projects) throws Exception {

        fillCard(content, x, y, w, h, Color.WHITE);

        float p = 18;
        float cursorY = y + h - 22;
        float bodyWidth = w - p * 2;

        String topSkillBlock = topSkills(skills);
        String snapshot = "Candidate from " + safe(dto.getUniversity(), "University")
                + " with strong focus on " + topSkillBlock
                + ". Demonstrates execution speed, practical delivery, and internship growth mindset.";

        cursorY = drawSection(content, x + p, cursorY, bodyWidth, "EXECUTIVE SNAPSHOT", snapshot, 11);
        cursorY -= 10;

        cursorY = drawMetricRow(content, x + p, cursorY, bodyWidth, skills.size(), projects.size());
        cursorY -= 8;

        cursorY = drawBulletSection(content, x + p, cursorY, bodyWidth, "IMPACT HIGHLIGHTS", projects);
        cursorY -= 8;

        drawSection(content, x + p, cursorY, bodyWidth,
                "HIRING SIGNAL",
                "High ownership, fast adaptation, and project-first mindset. Ready to contribute from week one with measurable outcomes.",
                11);
    }

    private float drawMetricRow(PDPageContentStream content,
                                float x,
                                float y,
                                float width,
                                int skillsCount,
                                int projectsCount) throws Exception {

        writeText(content, "IMPACT METRICS", FONT_BOLD, 12, x, y, new Color(18, 35, 71));
        float rowY = y - 16;

        float gap = 8;
        float cardW = (width - gap * 2) / 3f;
        float cardH = 44;

        drawMetricCard(content, x, rowY - cardH, cardW, cardH, "Skills", String.valueOf(skillsCount));
        drawMetricCard(content, x + cardW + gap, rowY - cardH, cardW, cardH, "Projects", String.valueOf(projectsCount));
        drawMetricCard(content, x + (cardW + gap) * 2, rowY - cardH, cardW, cardH, "Readiness", "A+");

        return rowY - cardH - 8;
    }

    private void drawMetricCard(PDPageContentStream content,
                                float x,
                                float y,
                                float w,
                                float h,
                                String label,
                                String value) throws Exception {

        content.setNonStrokingColor(new Color(237, 244, 255));
        content.addRect(x, y, w, h);
        content.fill();

        content.setStrokingColor(new Color(211, 224, 249));
        content.addRect(x, y, w, h);
        content.stroke();

        writeText(content, label, FONT_REGULAR, 9, x + 8, y + h - 12, new Color(83, 103, 142));
        writeText(content, value, FONT_BOLD, 16, x + 8, y + 12, new Color(24, 46, 101));
    }

    private float drawSection(PDPageContentStream content,
                              float x,
                              float y,
                              float width,
                              String title,
                              String body,
                              int bodySize) throws Exception {

        writeText(content, title, FONT_BOLD, 12, x, y, new Color(18, 35, 71));

        content.setStrokingColor(new Color(227, 236, 252));
        content.moveTo(x, y - 5);
        content.lineTo(x + width, y - 5);
        content.stroke();

        float textY = y - 20;
        for (String line : wrap(body, FONT_REGULAR, bodySize, width)) {
            writeText(content, line, FONT_REGULAR, bodySize, x, textY, new Color(70, 88, 123));
            textY -= 15;
        }

        return textY;
    }

    private float drawBulletSection(PDPageContentStream content,
                                    float x,
                                    float y,
                                    float width,
                                    String title,
                                    List<String> bullets) throws Exception {

        writeText(content, title, FONT_BOLD, 12, x, y, new Color(18, 35, 71));

        content.setStrokingColor(new Color(227, 236, 252));
        content.moveTo(x, y - 5);
        content.lineTo(x + width, y - 5);
        content.stroke();

        float textY = y - 20;
        for (String bullet : bullets) {
            List<String> lines = wrap(bullet, FONT_REGULAR, 11, width - 14);
            if (lines.isEmpty()) {
                continue;
            }

            writeText(content, "-", FONT_BOLD, 11, x, textY, new Color(58, 83, 140));
            writeText(content, lines.get(0), FONT_REGULAR, 11, x + 12, textY, new Color(70, 88, 123));
            textY -= 15;

            for (int i = 1; i < lines.size(); i++) {
                writeText(content, lines.get(i), FONT_REGULAR, 11, x + 12, textY, new Color(70, 88, 123));
                textY -= 15;
            }
            textY -= 2;
        }

        return textY;
    }

    private void drawBottomBar(PDPageContentStream content, float pageWidth) throws Exception {
        float margin = 34;
        float barY = 24;

        content.setNonStrokingColor(new Color(227, 237, 255));
        content.addRect(margin, barY, pageWidth - margin * 2, 18);
        content.fill();

        writeText(content, "Generated by SkillBridge AI Resume Studio", FONT_BOLD, 9, margin + 8, barY + 5, new Color(56, 80, 129));
    }

    private void drawChip(PDPageContentStream content, float x, float y, String text, Color color) throws Exception {
        float width = 70 + text.length() * 2.2f;
        content.setNonStrokingColor(color);
        content.addRect(x, y, width, 18);
        content.fill();
        writeText(content, text, FONT_BOLD, 8, x + 8, y + 5, Color.WHITE);
    }

    private float drawBadge(PDPageContentStream content,
                            float x,
                            float y,
                            float width,
                            String text,
                            Color bg,
                            Color textColor) throws Exception {

        float h = 19;
        content.setNonStrokingColor(bg);
        content.addRect(x, y - h + 3, width, h);
        content.fill();

        writeText(content, text, FONT_BOLD, 9, x + 6, y - 8, textColor);
        return y - 22;
    }

    private void fillCard(PDPageContentStream content,
                          float x,
                          float y,
                          float w,
                          float h,
                          Color color) throws Exception {
        content.setNonStrokingColor(color);
        content.addRect(x, y, w, h);
        content.fill();
    }

    private void writeText(PDPageContentStream content,
                           String text,
                           PDFont font,
                           int size,
                           float x,
                           float y,
                           Color color) throws Exception {

        content.beginText();
        content.setNonStrokingColor(color);
        content.setFont(font, size);
        content.newLineAtOffset(x, y);
        content.showText(safeText(text));
        content.endText();
    }

    private List<String> wrap(String text, PDFont font, int size, float maxWidth) throws Exception {
        List<String> lines = new ArrayList<>();
        String value = safeText(text);

        StringBuilder line = new StringBuilder();
        for (String word : value.split("\\s+")) {
            String candidate = line.isEmpty() ? word : line + " " + word;
            float width = font.getStringWidth(candidate) / 1000 * size;
            if (width <= maxWidth) {
                line = new StringBuilder(candidate);
            } else {
                if (!line.isEmpty()) {
                    lines.add(line.toString());
                }
                line = new StringBuilder(word);
            }
        }

        if (!line.isEmpty()) {
            lines.add(line.toString());
        }

        return lines;
    }

    private List<String> splitItems(String source, int maxItems) {
        List<String> out = new ArrayList<>();
        if (source == null || source.isBlank()) {
            return out;
        }

        for (String item : source.split(",")) {
            String value = safeText(item.trim());
            if (!value.isBlank()) {
                out.add(value);
            }
            if (out.size() >= maxItems) {
                break;
            }
        }
        return out;
    }

    private List<String> splitProjects(String source) {
        List<String> raw = new ArrayList<>();
        if (source == null || source.isBlank()) {
            return List.of("Built a production-ready internship platform with measurable outcomes.");
        }

        String normalized = source.replace("|", ";");
        for (String part : normalized.split(";")) {
            String value = safeText(part.trim());
            if (!value.isBlank()) {
                raw.add(value);
            }
            if (raw.size() >= 5) {
                break;
            }
        }

        if (raw.isEmpty()) {
            raw.add("Built a production-ready internship platform with measurable outcomes.");
        }

        return raw;
    }

    private String topSkills(List<String> skills) {
        if (skills == null || skills.isEmpty()) {
            return "core software development";
        }

        int n = Math.min(3, skills.size());
        return String.join(", ", skills.subList(0, n));
    }

    private String safe(String value, String fallback) {
        if (value == null || value.isBlank()) {
            return fallback;
        }
        return value.trim();
    }

    private String safeText(String value) {
        if (value == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder(value.length());
        for (char c : value.toCharArray()) {
            if (c >= 32 && c <= 255) {
                sb.append(c);
            } else {
                sb.append(' ');
            }
        }

        return sb.toString().replaceAll("\\s+", " ").trim();
    }
}
