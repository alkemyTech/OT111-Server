package com.alkemy.ong.model.response;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

    private Long id;

    private String name;

    private String description;

    private String image;
    private OffsetDateTime createdDate;
}

