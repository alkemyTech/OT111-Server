package com.alkemy.ong.service;

import com.alkemy.ong.model.request.user.UserRequestDTO;
import com.alkemy.ong.model.response.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> findAllUsers();

    UserResponseDTO findUserById(Long id);

    void updateUser(UserRequestDTO request, Long id);

    void deleteUser(Long id);
}
