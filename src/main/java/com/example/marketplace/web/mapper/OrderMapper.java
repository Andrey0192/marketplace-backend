package com.example.marketplace.web.mapper;

import com.example.marketplace.domain.entity.ListingEntity;
import com.example.marketplace.domain.entity.OrderEntity;
import com.example.marketplace.domain.entity.UserEntity;
import com.example.marketplace.web.dto.order.OrderCreateRequest;
import com.example.marketplace.web.dto.order.OrderResponse;
import com.example.marketplace.web.dto.order.OrderUpdateRequest;

public final class OrderMapper {

    private OrderMapper() {}

    public static OrderEntity toEntity(OrderCreateRequest req, ListingEntity listing, UserEntity buyer) {
        OrderEntity e = new OrderEntity();
        e.setListing(listing);
        e.setBuyer(buyer);
        e.setAmount(req.amount());
        e.setStatus(req.status());
        return e;
    }

    public static void apply(OrderEntity e, OrderUpdateRequest req) {
        e.setAmount(req.amount());
        e.setStatus(req.status());
    }

    public static OrderResponse toResponse(OrderEntity e) {
        return new OrderResponse(
                e.getId(),
                e.getListing().getId(),
                e.getBuyer().getId(),
                e.getAmount(),
                e.getStatus(),
                e.getCreatedAt()
        );
    }
}
