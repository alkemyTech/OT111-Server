package com.alkemy.ong.utils;


import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.request.CategoryRequestDTO;

public class Mocks {

    public static CategoryEntity buildCategoryEntity() {
        return CategoryEntity.builder()
                .name("Mock Category")
                .description("Mock Description")
                .image("Mock Image")
                .build();
    }

    public static CategoryRequestDTO buildCategoryRequest() {
        return CategoryRequestDTO.builder()
                .name("name mock")
                .description("description mock")
                .image("url mock")
                .build();
    }

    public static CategoryRequestDTO buildCategoryRequestInvalid() {
        return CategoryRequestDTO.builder()
                .name("")
                .description("description mock")
                .image("url mock")
                .build();
    }

}
