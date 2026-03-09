package com.skillbridge.skillbridgebackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @Email(message = "Email формат дұрыс емес")
    @NotBlank(message = "Email бос болмауы керек")
    private String email;

    @NotBlank(message = "Password бос болмауы керек")
    private String password;
}
