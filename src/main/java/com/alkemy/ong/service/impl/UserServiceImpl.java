package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.mapper.UserMapper;
import com.alkemy.ong.model.request.user.UserUpdateDTO;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.response.user.UserDTO;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userMapper.entity2DTO(userEntityList);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findUserById(Long id) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow();
        return userMapper.entity2DTO(foundUser);
    }

    @Override
    @Transactional
    public void updateUser(UserUpdateDTO request, Long id) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow();
        foundUser.setFirstName(request.getFirstName());
        foundUser.setLastName(request.getLastName());
        foundUser.setPassword(passwordEncoder.encode(request.getPassword()));
        foundUser.setEmail(request.getEmail());
        foundUser.setPhoto(request.getPhoto());
        userRepository.save(foundUser);
    }


}
