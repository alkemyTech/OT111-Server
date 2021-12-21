package com.alkemy.ong.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    @NotEmpty(message = "El nombre es obligatorio")
    private String name;

    private String description;

    private String image;

}
