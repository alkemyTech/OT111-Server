package com.alkemy.ong.model.response.news;


import com.alkemy.ong.model.entity.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@Builder
public class NewsDTO {

    private Long id;

    private String name;

    private String content;

    private String image;

}
