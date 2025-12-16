package com.example.marketplace.service;

import com.example.marketplace.domain.enums.ListingStatus;
import com.example.marketplace.web.dto.listing.ListingCreateRequest;
import com.example.marketplace.web.dto.listing.ListingResponse;
import com.example.marketplace.web.dto.listing.ListingUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListingService {
    ListingResponse create(ListingCreateRequest req);
    ListingResponse getById(Long id);
    Page<ListingResponse> list(Long categoryId, Long sellerId, ListingStatus status, Pageable pageable);
    ListingResponse update(Long id, ListingUpdateRequest req);
    void delete(Long id);
}
