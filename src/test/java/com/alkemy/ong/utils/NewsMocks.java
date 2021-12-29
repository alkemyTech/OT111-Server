package com.alkemy.ong.utils;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.entity.NewsEntity;
import com.alkemy.ong.model.request.NewsRequestDTO;

public class NewsMocks {

    public static CategoryEntity buildCategoryEntity() {
        return CategoryEntity.builder()
                .name("Mock Category")
                .description("Mock Description")
                .image("Mock Image")
                .build();
    }

    public static NewsEntity buildNewsEntity() {
        return NewsEntity.builder()
                .name("MockNews name")
                .content("MockNews content")
                .image("MockNews image")
                .category(buildCategoryEntity())
                .build();
    }

    public static NewsRequestDTO buildNewsRequest(Long id) {
        return NewsRequestDTO.builder()
                .name("name newsMock")
                .content("content newsMock")
                .image("url newsMock")
                .categoryId(id)
                .build();
    }

    public static NewsRequestDTO buildNewsRequestInvalid(Long id) {
        return NewsRequestDTO.builder()
                .name("")
                .content("")
                .image("")
                .categoryId(id)
                .build();
    }

}
