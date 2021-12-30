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

<<<<<<< HEAD
    public static NewsRequestDTO buildNewsRequest() {
=======
    public static NewsRequestDTO buildNewsRequest(Long id) {
>>>>>>> f78cb153c07c41f1d75805da12fd111897ab7ec9
        return NewsRequestDTO.builder()
                .name("name newsMock")
                .content("content newsMock")
                .image("url newsMock")
<<<<<<< HEAD
                .categoryId(12L)
                .build();
    }

    public static NewsRequestDTO buildNewsRequestInvalid() {
=======
                .categoryId(id)
                .build();
    }

    public static NewsRequestDTO buildNewsRequestInvalid(Long id) {
>>>>>>> f78cb153c07c41f1d75805da12fd111897ab7ec9
        return NewsRequestDTO.builder()
                .name("")
                .content("")
                .image("")
<<<<<<< HEAD
                .categoryId(buildNewsRequest().getCategoryId())
=======
                .categoryId(id)
>>>>>>> f78cb153c07c41f1d75805da12fd111897ab7ec9
                .build();
    }

}
