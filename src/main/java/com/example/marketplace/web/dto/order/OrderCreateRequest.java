package com.example.marketplace.web.dto.order;

import com.example.marketplace.domain.enums.OrderStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderCreateRequest(
        @NotNull Long listingId,
        @NotNull Long buyerId,
        @NotNull @DecimalMin(value = "0.00", inclusive = true) BigDecimal amount,
        @NotNull OrderStatus status
) {}
