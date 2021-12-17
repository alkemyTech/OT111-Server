package com.alkemy.ong.utils;


import com.alkemy.ong.model.entity.CategoryEntity;

public class Mocks {

    public static CategoryEntity newCategory() {
        return CategoryEntity.builder()
                .id(999L)
                .name("Mock Category")
                .description("Mock Description")
                .image("Mock Image")
                .build();
    }


}
