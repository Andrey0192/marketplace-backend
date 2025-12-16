package com.example.marketplace.web.controller;

import com.example.marketplace.domain.enums.ListingStatus;
import com.example.marketplace.service.ListingService;
import com.example.marketplace.web.dto.listing.ListingCreateRequest;
import com.example.marketplace.web.dto.listing.ListingResponse;
import com.example.marketplace.web.dto.listing.ListingUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/listings")
public class ListingController {

    private final ListingService listings;

    public ListingController(ListingService listings) {
        this.listings = listings;
    }

    @PostMapping
    public ResponseEntity<ListingResponse> create(@Valid @RequestBody ListingCreateRequest req) {
        ListingResponse created = listings.create(req);
        return ResponseEntity.created(URI.create("/api/listings/" + created.id())).body(created);
    }

    @GetMapping("/{id}")
    public ListingResponse get(@PathVariable Long id) {
        return listings.getById(id);
    }

    @GetMapping
    public Page<ListingResponse> list(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long sellerId,
            @RequestParam(required = false) ListingStatus status,
            @PageableDefault(size = 20, sort = "id") Pageable pageable
    ) {
        return listings.list(categoryId, sellerId, status, pageable);
    }

    @PutMapping("/{id}")
    public ListingResponse update(@PathVariable Long id, @Valid @RequestBody ListingUpdateRequest req) {
        return listings.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        listings.delete(id);
        return ResponseEntity.noContent().build();
    }
}
