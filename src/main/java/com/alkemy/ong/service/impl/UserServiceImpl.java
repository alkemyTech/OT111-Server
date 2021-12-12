package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.mapper.UserMapper;
import com.alkemy.ong.model.response.user.UserDTO;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDTO> findAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userMapper.entity2DTO(userEntityList);
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        try {
            Optional<UserEntity> optionalUser = userRepository.findById(id);
            UserEntity user = optionalUser.get();
            user.setDeleted(true);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
