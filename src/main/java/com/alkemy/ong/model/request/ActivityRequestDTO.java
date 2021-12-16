package com.alkemy.ong.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ActivityRequestDTO {

    @NotEmpty(message = "El nombre es obligatorio")
    private String name;

    private String content;

    private String image;
}
