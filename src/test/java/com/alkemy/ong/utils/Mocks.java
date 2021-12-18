package com.alkemy.ong.utils;


import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.request.CategoryRequestDTO;

public class Mocks {

    public static CategoryEntity newCategory() {
        return CategoryEntity.builder()
                .id(999L)
                .name("Mock Category")
                .description("Mock Description")
                .image("Mock Image")
                .build();
    }

    public static CategoryEntity updatedCategory() {
        return CategoryEntity.builder()
                .id(999L)
                .name("Updated Category")
                .description("Updated Description")
                .image("Updated Image")
                .build();
    }

    public static CategoryRequestDTO newCategoryRequestDTO() {
        CategoryRequestDTO req = new CategoryRequestDTO();
        req.setName("Request");
        req.setName("Request");
        req.setName("Request");
        return req;
    }


}
