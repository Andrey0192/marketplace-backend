package com.example.marketplace.web.controller;

import com.example.marketplace.service.CategoryService;
import com.example.marketplace.web.dto.category.CategoryCreateRequest;
import com.example.marketplace.web.dto.category.CategoryResponse;
import com.example.marketplace.web.dto.category.CategoryUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categories;

    public CategoryController(CategoryService categories) {
        this.categories = categories;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryCreateRequest req) {
        CategoryResponse created = categories.create(req);
        return ResponseEntity.created(URI.create("/api/categories/" + created.id())).body(created);
    }

    @GetMapping("/{id}")
    public CategoryResponse get(@PathVariable Long id) {
        return categories.getById(id);
    }

    @GetMapping
    public Page<CategoryResponse> list(@PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return categories.list(pageable);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @Valid @RequestBody CategoryUpdateRequest req) {
        return categories.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categories.delete(id);
        return ResponseEntity.noContent().build();
    }
}
