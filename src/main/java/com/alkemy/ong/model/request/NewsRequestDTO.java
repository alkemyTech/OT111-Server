package com.alkemy.ong.model.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewsRequestDTO {

    private Long id;

    private String name;

    private String content;

    private String image;

    private CategoryRequestDTO categoryRequestDTO;

    private String modifiedBy;
}
