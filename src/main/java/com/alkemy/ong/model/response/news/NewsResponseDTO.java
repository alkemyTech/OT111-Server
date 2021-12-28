package com.alkemy.ong.model.response.news;

import com.alkemy.ong.model.response.CategoryResponseDTO;
import lombok.Builder;
import lombok.Data;


@Data
@Builder

public class NewsResponseDTO {

    private String name;

    private String content;

    private String image;

    private CategoryResponseDTO category;

}
