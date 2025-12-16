package com.example.marketplace.service;

import com.example.marketplace.web.dto.user.UserCreateRequest;
import com.example.marketplace.web.dto.user.UserResponse;
import com.example.marketplace.web.dto.user.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponse create(UserCreateRequest req);
    UserResponse getById(Long id);
    Page<UserResponse> list(Pageable pageable);
    UserResponse update(Long id, UserUpdateRequest req);
    void delete(Long id);
}
