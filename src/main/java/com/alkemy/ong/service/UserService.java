package com.alkemy.ong.service;

import com.alkemy.ong.model.request.UserRequest;
import com.alkemy.ong.model.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAllUsers();

    UserResponse findUserById(Long id);

    void updateUser(UserRequest request, Long id);

    void deleteUser(Long id);
}
