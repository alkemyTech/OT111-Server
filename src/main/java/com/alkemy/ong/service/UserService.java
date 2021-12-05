package com.alkemy.ong.service;

import com.alkemy.ong.model.request.user.UserUpdateDTO;
import com.alkemy.ong.model.response.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> findAllUsers();

    UserResponseDTO findById(Long id);

    UserUpdateDTO updateUser(UserUpdateDTO UserUpdateDTO, Long id);
}
