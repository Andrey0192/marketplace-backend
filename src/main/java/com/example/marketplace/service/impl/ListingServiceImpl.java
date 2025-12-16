package com.example.marketplace.service.impl;

import com.example.marketplace.domain.entity.CategoryEntity;
import com.example.marketplace.domain.entity.ListingEntity;
import com.example.marketplace.domain.entity.UserEntity;
import com.example.marketplace.domain.enums.ListingStatus;
import com.example.marketplace.exception.NotFoundException;
import com.example.marketplace.repository.CategoryRepository;
import com.example.marketplace.repository.ListingRepository;
import com.example.marketplace.repository.UserRepository;
import com.example.marketplace.service.ListingService;
import com.example.marketplace.web.dto.listing.ListingCreateRequest;
import com.example.marketplace.web.dto.listing.ListingResponse;
import com.example.marketplace.web.dto.listing.ListingUpdateRequest;
import com.example.marketplace.web.mapper.ListingMapper;
import com.example.marketplace.web.specification.ListingSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ListingServiceImpl implements ListingService {

    private final ListingRepository listings;
    private final CategoryRepository categories;
    private final UserRepository users;

    public ListingServiceImpl(ListingRepository listings, CategoryRepository categories, UserRepository users) {
        this.listings = listings;
        this.categories = categories;
        this.users = users;
    }

    @Override
    @Transactional
    public ListingResponse create(ListingCreateRequest req) {
        CategoryEntity category = getCategory(req.categoryId());
        UserEntity seller = getUser(req.sellerId());

        ListingEntity e = ListingMapper.toEntity(req, category, seller);
        return ListingMapper.toResponse(listings.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public ListingResponse getById(Long id) {
        return ListingMapper.toResponse(getEntity(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ListingResponse> list(Long categoryId, Long sellerId, ListingStatus status, Pageable pageable) {
        Specification<ListingEntity> spec = Specification
                .where(ListingSpecifications.hasCategoryId(categoryId))
                .and(ListingSpecifications.hasSellerId(sellerId))
                .and(ListingSpecifications.hasStatus(status));

        return listings.findAll(spec, pageable).map(ListingMapper::toResponse);
    }

    @Override
    @Transactional
    public ListingResponse update(Long id, ListingUpdateRequest req) {
        ListingEntity e = getEntity(id);
        CategoryEntity category = getCategory(req.categoryId());
        UserEntity seller = getUser(req.sellerId());

        ListingMapper.apply(e, req, category, seller);
        return ListingMapper.toResponse(listings.save(e));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!listings.existsById(id)) {
            throw new NotFoundException("Listing not found: id=" + id);
        }
        listings.deleteById(id);
    }

    private ListingEntity getEntity(Long id) {
        return listings.findById(id).orElseThrow(() -> new NotFoundException("Listing not found: id=" + id));
    }

    private CategoryEntity getCategory(Long id) {
        return categories.findById(id).orElseThrow(() -> new NotFoundException("Category not found: id=" + id));
    }

    private UserEntity getUser(Long id) {
        return users.findById(id).orElseThrow(() -> new NotFoundException("User not found: id=" + id));
    }
}
