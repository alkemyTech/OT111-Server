package com.alkemy.ong.utils;


import com.alkemy.ong.model.entity.CategoryEntity;

public class Mocks {

    public static CategoryEntity newCategory() {
        return CategoryEntity.builder()
                .id(1L)
                .name("Category 1")
                .description("Description 1")
                .image("Image 1")
                .build();
    }


}
