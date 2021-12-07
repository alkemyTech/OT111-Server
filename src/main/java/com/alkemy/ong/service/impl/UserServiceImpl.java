package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.mapper.UserMapper;
import com.alkemy.ong.model.request.user.UserUpdateDTO;
import com.alkemy.ong.model.response.user.UserResponseDTO;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userMapper.entity2DTO(userEntityList);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
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
//        if (!userUpdateDTO.getPassword().isEmpty()){
//            userEntity.get().setPassword(userUpdateDTO.getPassword()); //TODO Replace or insert Encoder method
//        }
            userEntity.get().setEmail(userUpdateDTO.getEmail());
            userEntity.get().setPhoto(userUpdateDTO.getPhoto());

            userRepository.save(userEntity.get());
            return userMapper.entityUpdate2DTO(userEntity.get());
        }
        return null;
    }


}
