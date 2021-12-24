package com.alkemy.ong.model.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    @NotEmpty(message = "El nombre es obligatorio")
    private String name;

    private String description;

    private String image;
}
