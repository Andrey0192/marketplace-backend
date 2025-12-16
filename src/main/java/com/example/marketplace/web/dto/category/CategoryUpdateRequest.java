package com.example.marketplace.web.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CategoryUpdateRequest(
        @NotBlank @Size(min = 2, max = 120) String name,
        @NotBlank
        @Size(min = 2, max = 160)
        @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "slug must be kebab-case (lowercase letters, digits, hyphens)")
        String slug
) {}
