package com.example.marketplace.service;

import com.example.marketplace.domain.enums.OrderStatus;
import com.example.marketplace.web.dto.order.OrderCreateRequest;
import com.example.marketplace.web.dto.order.OrderResponse;
import com.example.marketplace.web.dto.order.OrderUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse create(OrderCreateRequest req);
    OrderResponse getById(Long id);
    Page<OrderResponse> list(Long buyerId, OrderStatus status, Pageable pageable);
    OrderResponse update(Long id, OrderUpdateRequest req);
    void delete(Long id);
}
