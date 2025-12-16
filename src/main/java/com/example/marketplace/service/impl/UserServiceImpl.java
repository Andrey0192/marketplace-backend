package com.example.marketplace.service.impl;

import com.example.marketplace.domain.entity.UserEntity;
import com.example.marketplace.exception.NotFoundException;
import com.example.marketplace.repository.UserRepository;
import com.example.marketplace.service.UserService;
import com.example.marketplace.web.dto.user.UserCreateRequest;
import com.example.marketplace.web.dto.user.UserResponse;
import com.example.marketplace.web.dto.user.UserUpdateRequest;
import com.example.marketplace.web.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository users;

    public UserServiceImpl(UserRepository users) {
        this.users = users;
    }

    @Override
    @Transactional
    public UserResponse create(UserCreateRequest req) {
        UserEntity e = UserMapper.toEntity(req);
        return UserMapper.toResponse(users.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        return UserMapper.toResponse(getEntity(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> list(Pageable pageable) {
        return users.findAll(pageable).map(UserMapper::toResponse);
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UserUpdateRequest req) {
        UserEntity e = getEntity(id);
        UserMapper.apply(e, req);
        return UserMapper.toResponse(users.save(e));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!users.existsById(id)) {
            throw new NotFoundException("User not found: id=" + id);
        }
        users.deleteById(id);
    }

    private UserEntity getEntity(Long id) {
        return users.findById(id).orElseThrow(() -> new NotFoundException("User not found: id=" + id));
    }
}
