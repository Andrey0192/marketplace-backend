package com.example.marketplace.web.dto.listing;

import com.example.marketplace.domain.enums.ListingStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record ListingResponse(
        Long id,
        String title,
        String description,
        BigDecimal price,
        String currency,
        ListingStatus status,
        Long categoryId,
        Long sellerId,
        Instant createdAt
) {}
