package com.alkemy.ong.service;

import com.alkemy.ong.model.request.user.UserUpdateDTO;
import com.alkemy.ong.model.response.user.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();

    UserDTO findById(Long id);

    UserUpdateDTO updateUser(UserUpdateDTO UserUpdateDTO, Long id);
}
