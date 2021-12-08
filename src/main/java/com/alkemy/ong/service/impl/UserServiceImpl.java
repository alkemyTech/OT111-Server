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
    public UserDTO findById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (!userEntity.isEmpty()){
            return userMapper.entity2DTO(userEntity.get());
        }
        return null;
    }

    @Override
    @Transactional
    public UserUpdateDTO updateUser(UserUpdateDTO userUpdateDTO, Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isEmpty()){

            userEntity.get().setFirstName(userUpdateDTO.getFirstName());
            userEntity.get().setLastName(userUpdateDTO.getLastName());
        if (!userUpdateDTO.getPassword().isEmpty()){
            userEntity.get().setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        }
            userEntity.get().setEmail(userUpdateDTO.getEmail());
            userEntity.get().setPhoto(userUpdateDTO.getPhoto());

            userRepository.save(userEntity.get());
            return userMapper.entityUpdate2DTO(userEntity.get());
        }
        return null;
    }


}
