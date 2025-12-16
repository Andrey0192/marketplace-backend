package com.example.marketplace.web.specification;

import com.example.marketplace.domain.entity.ListingEntity;
import com.example.marketplace.domain.enums.ListingStatus;
import org.springframework.data.jpa.domain.Specification;

public final class ListingSpecifications {

    private ListingSpecifications() {}

    public static Specification<ListingEntity> hasCategoryId(Long categoryId) {
        return (root, query, cb) -> categoryId == null
                ? cb.conjunction()
                : cb.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<ListingEntity> hasSellerId(Long sellerId) {
        return (root, query, cb) -> sellerId == null
                ? cb.conjunction()
                : cb.equal(root.get("seller").get("id"), sellerId);
    }

    public static Specification<ListingEntity> hasStatus(ListingStatus status) {
        return (root, query, cb) -> status == null
                ? cb.conjunction()
                : cb.equal(root.get("status"), status);
    }
}
