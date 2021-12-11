package com.alkemy.ong.model.response.category;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CategoryDTO {

    private Long id;

    private String name;

    private String description;

    private String image;

}
