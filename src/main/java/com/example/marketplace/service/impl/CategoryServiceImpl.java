package com.example.marketplace.service.impl;

import com.example.marketplace.domain.entity.CategoryEntity;
import com.example.marketplace.exception.NotFoundException;
import com.example.marketplace.repository.CategoryRepository;
import com.example.marketplace.service.CategoryService;
import com.example.marketplace.web.dto.category.CategoryCreateRequest;
import com.example.marketplace.web.dto.category.CategoryResponse;
import com.example.marketplace.web.dto.category.CategoryUpdateRequest;
import com.example.marketplace.web.mapper.CategoryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categories;

    public CategoryServiceImpl(CategoryRepository categories) {
        this.categories = categories;
    }

    @Override
    @Transactional
    public CategoryResponse create(CategoryCreateRequest req) {
        CategoryEntity e = CategoryMapper.toEntity(req);
        return CategoryMapper.toResponse(categories.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getById(Long id) {
        return CategoryMapper.toResponse(getEntity(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryResponse> list(Pageable pageable) {
        return categories.findAll(pageable).map(CategoryMapper::toResponse);
    }

    @Override
    @Transactional
    public CategoryResponse update(Long id, CategoryUpdateRequest req) {
        CategoryEntity e = getEntity(id);
        CategoryMapper.apply(e, req);
        return CategoryMapper.toResponse(categories.save(e));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!categories.existsById(id)) {
            throw new NotFoundException("Category not found: id=" + id);
        }
        categories.deleteById(id);
    }

    private CategoryEntity getEntity(Long id) {
        return categories.findById(id).orElseThrow(() -> new NotFoundException("Category not found: id=" + id));
    }
}
