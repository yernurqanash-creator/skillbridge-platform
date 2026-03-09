package com.skillbridge.skillbridgebackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoadmapRequest {

    @NotBlank(message = "Interest бос болмауы керек")
    private String interest;
}
