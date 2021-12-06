package com.alkemy.ong.service;

import com.alkemy.ong.model.response.user.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();


    boolean deleteUser(Long id);
}
