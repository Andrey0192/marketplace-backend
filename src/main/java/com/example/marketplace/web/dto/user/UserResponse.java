package com.example.marketplace.web.dto.user;

import com.example.marketplace.domain.enums.Role;

import java.time.Instant;

public record UserResponse(
        Long id,
        String username,
        String email,
        Role role,
        Instant createdAt
) {}
