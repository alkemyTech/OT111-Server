package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.RoleEntity;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.request.UserRequest;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return UserResponse.listToDTO(userEntityList);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findUserById(Long id) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow();
        return UserResponse.toDTO(foundUser);
    }

    @Override
    @Transactional
    public void updateUser(UserRequest request, Long id) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow();

        List<Integer> roleId = request.getRoles();
        List<RoleEntity> roleEntities = new ArrayList<>();

        for (Integer roleIntegerId : roleId) {
            roleEntities.add(roleRepository.getById(roleIntegerId));
        }

        userRepository.save(UserRequest.updateEntity(request, foundUser, roleEntities));

    }

    @Transactional
    public void deleteUser(Long id) {
        var foundUser = userRepository.findById(id).orElseThrow();
        userRepository.delete(foundUser);
    }

}
