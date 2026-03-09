package com.skillbridge.skillbridgebackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private Long id;
    private String name;
    private String email;
}
