package com.skillbridge.skillbridgebackend.exception;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private Instant timestamp;
    private int status;
    private String error;
    private String path;

    // бір немесе бірнеше қате (validation үшін де, басқа үшін де)
    private List<String> errors;
}