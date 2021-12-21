package com.alkemy.ong.utils;


import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.request.CategoryRequestDTO;

import java.time.OffsetDateTime;

public class Mocks {

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
