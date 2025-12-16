package com.example.marketplace.web.mapper;

import com.example.marketplace.domain.entity.CategoryEntity;
import com.example.marketplace.domain.entity.ListingEntity;
import com.example.marketplace.domain.entity.UserEntity;
import com.example.marketplace.web.dto.listing.ListingCreateRequest;
import com.example.marketplace.web.dto.listing.ListingResponse;
import com.example.marketplace.web.dto.listing.ListingUpdateRequest;

public final class ListingMapper {

    private ListingMapper() {}

    public static ListingEntity toEntity(ListingCreateRequest req, CategoryEntity category, UserEntity seller) {
        ListingEntity e = new ListingEntity();
        e.setTitle(req.title());
        e.setDescription(req.description());
        e.setPrice(req.price());
        e.setCurrency(req.currency());
        e.setStatus(req.status());
        e.setCategory(category);
        e.setSeller(seller);
        return e;
    }

    public static void apply(ListingEntity e, ListingUpdateRequest req, CategoryEntity category, UserEntity seller) {
        e.setTitle(req.title());
        e.setDescription(req.description());
        e.setPrice(req.price());
        e.setCurrency(req.currency());
        e.setStatus(req.status());
        e.setCategory(category);
        e.setSeller(seller);
    }

    public static ListingResponse toResponse(ListingEntity e) {
        return new ListingResponse(
                e.getId(),
                e.getTitle(),
                e.getDescription(),
                e.getPrice(),
                e.getCurrency(),
                e.getStatus(),
                e.getCategory().getId(),
                e.getSeller().getId(),
                e.getCreatedAt()
        );
    }
}
