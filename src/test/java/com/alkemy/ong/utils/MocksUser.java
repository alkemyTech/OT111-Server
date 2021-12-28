package com.alkemy.ong.utils;

import com.alkemy.ong.model.entity.RoleEntity;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.request.UserRequest;

import java.util.List;

public class MocksUser {

    public static RoleEntity newRoleAdmin() {

        return RoleEntity.builder().id(2).name("ROLE_ADMIN").build();
    }

    public static RoleEntity newRoleUser() {

        return RoleEntity.builder().id(1).name("ROLE_USER").build();
    }

    public static UserEntity buildUserEntity() {
        return UserEntity.builder()
                .firstName("Mock Username")
                .lastName("Mock Lastname")
                .email("mock@email.com")
                .password("password")
                .photo("Mock photo")
                .roles(List.of(newRoleUser()))
                .build();
    }

    public static UserRequest buildUserRequest() {
        return UserRequest.builder()
                .firstName("Mock firstname")
                .lastName("Mock lastname")
                .email("mock@email.com")
                .photo("mock.jpg")
                .roles(List.of(2))
                .build();
    }


}
