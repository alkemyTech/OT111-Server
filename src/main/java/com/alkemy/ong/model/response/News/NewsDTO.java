package com.alkemy.ong.model.response.News;


import com.alkemy.ong.model.entity.CategoryEntity;
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

    //private CategoryEntity categoryId;

    private OffsetDateTime createdDate;

    private OffsetDateTime modifiedDate;

    private String createdBy;

    private String modifiedBy;

    private boolean deleted = Boolean.FALSE;


}
