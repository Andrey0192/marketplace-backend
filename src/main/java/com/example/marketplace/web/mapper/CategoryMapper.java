package com.example.marketplace.web.mapper;

import com.example.marketplace.domain.entity.CategoryEntity;
import com.example.marketplace.web.dto.category.CategoryCreateRequest;
import com.example.marketplace.web.dto.category.CategoryResponse;
import com.example.marketplace.web.dto.category.CategoryUpdateRequest;

public final class CategoryMapper {

    private CategoryMapper() {}

    public static CategoryEntity toEntity(CategoryCreateRequest req) {
        CategoryEntity e = new CategoryEntity();
        e.setName(req.name());
        e.setSlug(req.slug());
        return e;
    }

    public static void apply(CategoryEntity e, CategoryUpdateRequest req) {
        e.setName(req.name());
        e.setSlug(req.slug());
    }

    public static CategoryResponse toResponse(CategoryEntity e) {
        return new CategoryResponse(e.getId(), e.getName(), e.getSlug());
    }
}
