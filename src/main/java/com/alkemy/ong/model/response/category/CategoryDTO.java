package com.alkemy.ong.model.response.category;

import lombok.Builder;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@Builder
public class CategoryDTO {

    private Long id;

    private String name;

    private String description;

    private String image;

    private OffsetDateTime createdDate;

    private OffsetDateTime modifiedDate;

    private String createdBy;

    private String modifiedBy;

    private boolean deleted = Boolean.FALSE;

}
