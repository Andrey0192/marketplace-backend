package com.example.marketplace.web.dto.order;

import com.example.marketplace.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderResponse(
        Long id,
        Long listingId,
        Long buyerId,
        BigDecimal amount,
        OrderStatus status,
        Instant createdAt
) {}
