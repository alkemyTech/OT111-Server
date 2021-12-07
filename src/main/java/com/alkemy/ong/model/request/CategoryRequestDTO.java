package com.alkemy.ong.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CategoryRequestDTO {

    @NotEmpty(message = "El nombre es obligatorio")
    private String name;

    private String description;

    private String image;

}
