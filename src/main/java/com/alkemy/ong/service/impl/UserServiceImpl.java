package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.RoleEntity;
import com.alkemy.ong.model.mapper.UserMapper;
import com.alkemy.ong.model.request.user.UserUpdateDTO;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.response.user.UserDTO;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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
        List <Integer> roleId = request.getRoles();
        List <RoleEntity> roleEntities = new ArrayList<>();

        for (Integer roleIntegerId: roleId){
            roleEntities.add(roleRepository.getById(roleIntegerId));
        }

        foundUser.setFirstName(request.getFirstName());
        foundUser.setLastName(request.getLastName());
        foundUser.setEmail(request.getEmail());
        foundUser.setPhoto(request.getPhoto());
        foundUser.setRoles(roleEntities);
        userRepository.save(foundUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        var foundUser = userRepository.findById(id).orElseThrow();
        userRepository.delete(foundUser);
    }

}
