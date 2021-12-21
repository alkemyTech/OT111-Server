package com.alkemy.ong.utils;

import com.alkemy.ong.model.entity.RoleEntity;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.mapper.RoleMapper;
import com.alkemy.ong.model.request.user.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.request.CategoryRequestDTO;

import java.time.OffsetDateTime;

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

    public static CategoryEntity newCategory() {
        return CategoryEntity.builder()
                .id(9999L)
                .name("Mock Category")
                .description("Mock Description")
                .image("Mock Image")
                .createdDate(OffsetDateTime.now())
                .build();
    }

    public static CategoryEntity updatedCategory() {
        return CategoryEntity.builder()
                .id(9999L)
                .name("Updated Category")
                .description("Updated Description")
                .image("Updated Image")
                .build();
    }

    public static CategoryRequestDTO newCategoryRequestDTO() {
        CategoryRequestDTO req = new CategoryRequestDTO();
        req.setName("Request");
        req.setDescription("Request");
        req.setImage("Request");
        return req;
    }

    public static CategoryRequestDTO categoryEntityWithConstraintViolation() {
        CategoryRequestDTO req = new CategoryRequestDTO();
        req.setDescription("Constraint Violation");
        req.setImage("Constraint Violation");
        return req;

    }

}
