package com.example.marketplace.web.dto.listing;

import com.example.marketplace.domain.enums.ListingStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ListingUpdateRequest(
        @NotBlank @Size(min = 3, max = 200) String title,
        @Size(max = 20_000) String description,
        @NotNull @DecimalMin(value = "0.00", inclusive = true) BigDecimal price,
        @NotBlank @Pattern(regexp = "^[A-Z]{3}$", message = "currency must be 3-letter code in uppercase") String currency,
        @NotNull ListingStatus status,
        @NotNull Long categoryId,
        @NotNull Long sellerId
) {}
