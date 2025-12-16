package com.example.marketplace.web.specification;

import com.example.marketplace.domain.entity.OrderEntity;
import com.example.marketplace.domain.enums.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

public final class OrderSpecifications {

    private OrderSpecifications() {}

    public static Specification<OrderEntity> hasBuyerId(Long buyerId) {
        return (root, query, cb) -> buyerId == null
                ? cb.conjunction()
                : cb.equal(root.get("buyer").get("id"), buyerId);
    }

    public static Specification<OrderEntity> hasStatus(OrderStatus status) {
        return (root, query, cb) -> status == null
                ? cb.conjunction()
                : cb.equal(root.get("status"), status);
    }
}
