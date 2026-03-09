package com.skillbridge.skillbridgebackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Name бос болмауы керек")
    private String name;

    @Email(message = "Email формат дұрыс емес")
    @NotBlank(message = "Email бос болмауы керек")
    private String email;
}