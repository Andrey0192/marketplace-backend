package com.example.marketplace.web.controller;

import com.example.marketplace.domain.enums.OrderStatus;
import com.example.marketplace.service.OrderService;
import com.example.marketplace.web.dto.order.OrderCreateRequest;
import com.example.marketplace.web.dto.order.OrderResponse;
import com.example.marketplace.web.dto.order.OrderUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orders;

    public OrderController(OrderService orders) {
        this.orders = orders;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderCreateRequest req) {
        OrderResponse created = orders.create(req);
        return ResponseEntity.created(URI.create("/api/orders/" + created.id())).body(created);
    }

    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable Long id) {
        return orders.getById(id);
    }

    @GetMapping
    public Page<OrderResponse> list(
            @RequestParam(required = false) Long buyerId,
            @RequestParam(required = false) OrderStatus status,
            @PageableDefault(size = 20, sort = "id") Pageable pageable
    ) {
        return orders.list(buyerId, status, pageable);
    }

    @PutMapping("/{id}")
    public OrderResponse update(@PathVariable Long id, @Valid @RequestBody OrderUpdateRequest req) {
        return orders.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orders.delete(id);
        return ResponseEntity.noContent().build();
    }
}
