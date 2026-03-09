package com.skillbridge.skillbridgebackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsItemDTO {
    private String title;
    private String category;
    private String summary;
    private String url;
    private String publishedAt;
}
