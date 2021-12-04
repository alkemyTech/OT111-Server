package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.mapper.UserMapper;
import com.alkemy.ong.model.response.user.UserDTO;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean deleteUser(Long id) {
        try {
            UserEntity user = userRepository.getById(id);
            user.setDeleted(1);
            userRepository.save(user);
            return true;
        } catch(Exception e) {
            return false;
        }
    }


}
