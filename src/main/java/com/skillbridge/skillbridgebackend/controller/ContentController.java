package com.skillbridge.skillbridgebackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.skillbridgebackend.dto.NewsItemDTO;
import com.skillbridge.skillbridgebackend.dto.ResourceItemDTO;
import com.skillbridge.skillbridgebackend.service.NewsService;
import com.skillbridge.skillbridgebackend.service.ResourceService;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final NewsService newsService;
    private final ResourceService resourceService;

    public ContentController(NewsService newsService, ResourceService resourceService) {
        this.newsService = newsService;
        this.resourceService = resourceService;
    }

    @GetMapping("/news")
    public List<NewsItemDTO> news() {
        return newsService.latestNews();
    }

    @GetMapping("/resources")
    public List<ResourceItemDTO> resources() {
        return resourceService.resources();
    }
}
