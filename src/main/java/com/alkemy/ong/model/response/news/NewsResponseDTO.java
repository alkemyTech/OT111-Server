package com.alkemy.ong.model.response.news;

import com.alkemy.ong.model.response.CategoryResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@Data
public class NewsResponseDTO {

    private Long id;

    private String name;

    private String content;

    private String image;

    private CategoryResponseDTO categoryResponseDTO;

    private OffsetDateTime createdDate;

    private OffsetDateTime modifiedDate;

    private String createdBy;

    private String modifiedBy;
}
