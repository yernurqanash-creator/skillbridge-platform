package com.skillbridge.skillbridgebackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceItemDTO {
    private String title;
    private String type;
    private String description;
    private String link;
}
