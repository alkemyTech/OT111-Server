package com.alkemy.ong.utils;

import com.alkemy.ong.model.entity.RoleEntity;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.mapper.RoleMapper;
import com.alkemy.ong.model.request.user.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class Mocks {

    @Autowired
    RoleMapper roleMapper;

    public static RoleEntity newRoleUser() {
        return RoleEntity.builder().id(1).name("ROLE_USER").build();
    }

    public static RoleEntity newRoleAdmin() {
        return RoleEntity.builder().id(2).name("ROLE_ADMIN").build();
    }

    public static UserEntity newUser() {

        return UserEntity.builder()
                .id(100L)
                .firstName("Mock Name")
                .lastName("Mock LastName")
                .email("mock@email.com")
                .password("123456")
                .photo("src/mock.jpg")
                .roles(List.of(newRoleAdmin()))
                .build();
    }

    public static UserRequestDTO newUserRequestDTO() {
        return UserRequestDTO.builder()
                .firstName("Updated firstname")
                .lastName("Updated lastname")
                .email("update@email.com")
                .photo("src/image.jpg")
                .roles(List.of(1,2))
                .build();
    }

    public static UserEntity updatedUser() {
        return UserEntity.builder()
                .id(99L)
                .firstName("Name updated")
                .lastName("LastName updated")
                .email("Email updated")
                .photo("updated.jpg")
                .roles(List.of(newRoleAdmin()))
                .build();
    }


}
