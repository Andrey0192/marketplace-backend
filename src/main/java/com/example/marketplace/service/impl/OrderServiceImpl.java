package com.example.marketplace.service.impl;

import com.example.marketplace.domain.entity.ListingEntity;
import com.example.marketplace.domain.entity.OrderEntity;
import com.example.marketplace.domain.entity.UserEntity;
import com.example.marketplace.domain.enums.OrderStatus;
import com.example.marketplace.exception.NotFoundException;
import com.example.marketplace.repository.ListingRepository;
import com.example.marketplace.repository.OrderRepository;
import com.example.marketplace.repository.UserRepository;
import com.example.marketplace.service.OrderService;
import com.example.marketplace.web.dto.order.OrderCreateRequest;
import com.example.marketplace.web.dto.order.OrderResponse;
import com.example.marketplace.web.dto.order.OrderUpdateRequest;
import com.example.marketplace.web.mapper.OrderMapper;
import com.example.marketplace.web.specification.OrderSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orders;
    private final ListingRepository listings;
    private final UserRepository users;

    public OrderServiceImpl(OrderRepository orders, ListingRepository listings, UserRepository users) {
        this.orders = orders;
        this.listings = listings;
        this.users = users;
    }

    @Override
    @Transactional
    public OrderResponse create(OrderCreateRequest req) {
        ListingEntity listing = getListing(req.listingId());
        UserEntity buyer = getUser(req.buyerId());

        OrderEntity e = OrderMapper.toEntity(req, listing, buyer);
        return OrderMapper.toResponse(orders.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getById(Long id) {
        return OrderMapper.toResponse(getEntity(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderResponse> list(Long buyerId, OrderStatus status, Pageable pageable) {
        Specification<OrderEntity> spec = Specification
                .where(OrderSpecifications.hasBuyerId(buyerId))
                .and(OrderSpecifications.hasStatus(status));

        return orders.findAll(spec, pageable).map(OrderMapper::toResponse);
    }

    @Override
    @Transactional
    public OrderResponse update(Long id, OrderUpdateRequest req) {
        OrderEntity e = getEntity(id);
        OrderMapper.apply(e, req);
        return OrderMapper.toResponse(orders.save(e));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!orders.existsById(id)) {
            throw new NotFoundException("Order not found: id=" + id);
        }
        orders.deleteById(id);
    }

    private OrderEntity getEntity(Long id) {
        return orders.findById(id).orElseThrow(() -> new NotFoundException("Order not found: id=" + id));
    }

    private ListingEntity getListing(Long id) {
        return listings.findById(id).orElseThrow(() -> new NotFoundException("Listing not found: id=" + id));
    }

    private UserEntity getUser(Long id) {
        return users.findById(id).orElseThrow(() -> new NotFoundException("User not found: id=" + id));
    }
}
