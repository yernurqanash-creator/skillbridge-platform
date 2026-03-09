package com.skillbridge.skillbridgebackend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skillbridge.skillbridgebackend.dto.AuthResponse;
import com.skillbridge.skillbridgebackend.dto.LoginRequest;
import com.skillbridge.skillbridgebackend.dto.RegisterRequest;
import com.skillbridge.skillbridgebackend.entity.User;
import com.skillbridge.skillbridgebackend.entity.User.Role;
import com.skillbridge.skillbridgebackend.exception.BadRequestException;
import com.skillbridge.skillbridgebackend.exception.NotFoundException;
import com.skillbridge.skillbridgebackend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        userRepository.findByEmail(request.getEmail().trim().toLowerCase())
                .ifPresent(existing -> {
                    throw new BadRequestException("Бұл email тіркелген");
                });

        User user = User.builder()
                .name(request.getName().trim())
                .email(request.getEmail().trim().toLowerCase())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.STUDENT)
                .build();

        User saved = userRepository.save(user);
        return toAuth(saved);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail().trim().toLowerCase())
                .orElseThrow(() -> new BadRequestException("Email немесе пароль қате"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Email немесе пароль қате");
        }

        return toAuth(user);
    }

    public AuthResponse profile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User табылмады"));

        return toAuth(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User табылмады"));
    }

    public List<String> parseSkills(String skills) {
        if (skills == null || skills.isBlank()) {
            return new ArrayList<>();
        }

        Set<String> unique = Arrays.stream(skills.split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(s -> !s.isBlank())
                .collect(Collectors.toCollection(HashSet::new));

        return unique.stream().sorted().toList();
    }

    private AuthResponse toAuth(User user) {
        return AuthResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
