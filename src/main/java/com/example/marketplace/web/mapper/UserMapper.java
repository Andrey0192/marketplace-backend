package com.example.marketplace.web.mapper;

import com.example.marketplace.domain.entity.UserEntity;
import com.example.marketplace.web.dto.user.UserCreateRequest;
import com.example.marketplace.web.dto.user.UserResponse;
import com.example.marketplace.web.dto.user.UserUpdateRequest;

public final class UserMapper {

    private UserMapper() {}

    public static UserEntity toEntity(UserCreateRequest req) {
        UserEntity e = new UserEntity();
        e.setUsername(req.username());
        e.setEmail(req.email());
        e.setRole(req.role());
        return e;
    }

    public static void apply(UserEntity e, UserUpdateRequest req) {
        e.setUsername(req.username());
        e.setEmail(req.email());
        e.setRole(req.role());
    }

    public static UserResponse toResponse(UserEntity e) {
        return new UserResponse(
                e.getId(),
                e.getUsername(),
                e.getEmail(),
                e.getRole(),
                e.getCreatedAt()
        );
    }
}
