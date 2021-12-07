package com.alkemy.ong.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDTO {
    private String name;
    private String description;
    private String image;
}
