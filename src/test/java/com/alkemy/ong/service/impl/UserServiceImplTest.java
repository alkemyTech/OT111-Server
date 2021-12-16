package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.mapper.UserMapper;
import com.alkemy.ong.model.request.user.UserUpdateDTO;
import com.alkemy.ong.model.response.user.UserDTO;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl underTest;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Spy
    private UserMapper userMapper = new UserMapper();

    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl(userRepository, userMapper, passwordEncoder, roleRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllUsers() {

    }

    @Test
    void findUserById() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}