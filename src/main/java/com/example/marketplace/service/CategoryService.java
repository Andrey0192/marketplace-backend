package com.example.marketplace.service;

import com.example.marketplace.web.dto.category.CategoryCreateRequest;
import com.example.marketplace.web.dto.category.CategoryResponse;
import com.example.marketplace.web.dto.category.CategoryUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryResponse create(CategoryCreateRequest req);
    CategoryResponse getById(Long id);
    Page<CategoryResponse> list(Pageable pageable);
    CategoryResponse update(Long id, CategoryUpdateRequest req);
    void delete(Long id);
}
