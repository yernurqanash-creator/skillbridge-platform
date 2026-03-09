package com.skillbridge.skillbridgebackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InternshipDTO {

    private Long id;

    @NotBlank(message = "Title бос болмауы керек")
    private String title;

    @NotBlank(message = "Company бос болмауы керек")
    private String companyName;

    @NotBlank(message = "Required skills бос болмауы керек")
    private String requiredSkills;

    private String description;
}