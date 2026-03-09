package com.skillbridge.skillbridgebackend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.skillbridge.skillbridgebackend.dto.RoadmapResponse;

@Service
public class CareerService {

    public RoadmapResponse buildRoadmap(String interest) {
        String track = normalizeTrack(interest);

        List<String> month1;
        List<String> month2;
        List<String> month3;

        switch (track) {
            case "backend" -> {
                month1 = List.of(
                        "Java Core + OOP қайталау",
                        "Spring Boot REST API жобасы",
                        "PostgreSQL: schema, joins, indexes"
                );
                month2 = List.of(
                        "Spring Security + JWT basics",
                        "Docker-compose арқылы сервис іске қосу",
                        "Unit/Integration тесттер"
                );
                month3 = List.of(
                        "Production-ready backend project",
                        "CI/CD + deployment (Render/Railway)",
                        "Backend internship interview prep"
                );
            }
            case "frontend" -> {
                month1 = List.of(
                        "HTML/CSS responsive layout",
                        "JavaScript ES6 + fetch",
                        "React компоненттері және state"
                );
                month2 = List.of(
                        "React Router + forms",
                        "UI/UX polish + accessibility",
                        "API integration + error states"
                );
                month3 = List.of(
                        "Production-ready frontend project",
                        "Performance optimization",
                        "Frontend interview prep"
                );
            }
            case "data" -> {
                month1 = List.of(
                        "Python + pandas негізі",
                        "SQL аналитика (GROUP BY, window functions)",
                        "Jupyter-да mini analysis project"
                );
                month2 = List.of(
                        "ML негізі: classification/regression",
                        "Feature engineering practice",
                        "Model evaluation + visualization"
                );
                month3 = List.of(
                        "End-to-end data project (data to dashboard)",
                        "Portfolio case study жазу",
                        "Data internship interview prep"
                );
            }
            case "devops" -> {
                month1 = List.of(
                        "Linux CLI + networking fundamentals",
                        "Docker image/container lifecycle",
                        "Git branching workflow"
                );
                month2 = List.of(
                        "Kubernetes basics + manifests",
                        "CI pipeline (GitHub Actions)",
                        "Monitoring with Prometheus/Grafana intro"
                );
                month3 = List.of(
                        "Deploy scalable app to cloud",
                        "Infra as Code (Terraform) basics",
                        "DevOps interview prep"
                );
            }
            case "mobile" -> {
                month1 = List.of(
                        "Kotlin/Swift немесе Flutter fundamentals",
                        "App navigation + local state",
                        "REST API integration"
                );
                month2 = List.of(
                        "Offline storage + auth flow",
                        "Push notifications basics",
                        "Performance profiling"
                );
                month3 = List.of(
                        "Publish-ready mobile app",
                        "Crash analytics + QA cycle",
                        "Mobile internship interview prep"
                );
            }
            case "qa" -> {
                month1 = List.of(
                        "Testing fundamentals + test cases",
                        "Postman API testing",
                        "Bug reporting workflow"
                );
                month2 = List.of(
                        "UI automation with Selenium/Playwright",
                        "API automation framework",
                        "Regression suite structure"
                );
                month3 = List.of(
                        "CI-integrated test pipeline",
                        "Quality metrics dashboard",
                        "QA internship interview prep"
                );
            }
            case "product" -> {
                month1 = List.of(
                        "Product discovery basics",
                        "User interviews + JTBD",
                        "PRD writing practice"
                );
                month2 = List.of(
                        "Prioritization frameworks (RICE/MoSCoW)",
                        "Analytics events and funnels",
                        "Roadmap planning"
                );
                month3 = List.of(
                        "Launch one feature end-to-end",
                        "A/B test basics",
                        "Product internship interview prep"
                );
            }
            case "uiux" -> {
                month1 = List.of(
                        "Design principles + hierarchy",
                        "Figma components & auto-layout",
                        "User flow mapping"
                );
                month2 = List.of(
                        "Wireframe to high-fidelity screens",
                        "Design system tokens",
                        "Usability testing"
                );
                month3 = List.of(
                        "Portfolio-ready case study",
                        "Prototype + handoff to dev",
                        "UI/UX internship interview prep"
                );
            }
            case "cybersecurity" -> {
                month1 = List.of(
                        "Security fundamentals + OWASP Top 10",
                        "Linux hardening basics",
                        "Network scanning practice"
                );
                month2 = List.of(
                        "Web security testing lab",
                        "SIEM basics + log analysis",
                        "Incident response workflow"
                );
                month3 = List.of(
                        "Security project report",
                        "Threat modeling practice",
                        "Cybersecurity internship interview prep"
                );
            }
            case "cloud" -> {
                month1 = List.of(
                        "Cloud fundamentals (AWS/Azure/GCP)",
                        "Compute + storage + IAM",
                        "Deploy first cloud app"
                );
                month2 = List.of(
                        "Managed database + networking",
                        "Auto-scaling + load balancing",
                        "Cloud cost monitoring basics"
                );
                month3 = List.of(
                        "Cloud architecture mini project",
                        "Security best practices",
                        "Cloud internship interview prep"
                );
            }
            default -> {
                month1 = List.of(
                        "CS fundamentals: algorithm + git",
                        "1 pet project таңдау",
                        "CV/LinkedIn базасын жаңарту"
                );
                month2 = List.of(
                        "2-ші mini project",
                        "Open-source issue шешіп көру",
                        "Soft skills: презентация және storytelling"
                );
                month3 = List.of(
                        "Capstone project",
                        "Portfolio толықтыру",
                        "Internship application sprint"
                );
            }
        }

        Set<String> recs = new HashSet<>();
        recs.add("Аптасына кемі 5 күн, күніне 2-3 сағат оқу режимін ұста");
        recs.add("Әр ай соңында progress review жаса");
        recs.add("GitHub-та commit history үздіксіз болсын");

        return RoadmapResponse.builder()
                .track(track)
                .month1(month1)
                .month2(month2)
                .month3(month3)
                .recommendations(recs.stream().toList())
                .build();
    }

    private String normalizeTrack(String interest) {
        if (interest == null) {
            return "general";
        }
        String value = interest.trim().toLowerCase(Locale.ROOT);

        if (value.contains("backend") || value.contains("бекенд") || value.contains("java")) return "backend";
        if (value.contains("frontend") || value.contains("фронтенд") || value.contains("react")) return "frontend";
        if (value.contains("data") || value.contains("ai") || value.contains("ml")) return "data";
        if (value.contains("devops") || value.contains("sre") || value.contains("docker")) return "devops";
        if (value.contains("mobile") || value.contains("android") || value.contains("ios") || value.contains("flutter")) return "mobile";
        if (value.contains("qa") || value.contains("test") || value.contains("automation")) return "qa";
        if (value.contains("product") || value.contains("pm")) return "product";
        if (value.contains("ui") || value.contains("ux") || value.contains("design")) return "uiux";
        if (value.contains("cyber") || value.contains("security") || value.contains("soc")) return "cybersecurity";
        if (value.contains("cloud") || value.contains("aws") || value.contains("gcp") || value.contains("azure")) return "cloud";

        return "general";
    }
}
