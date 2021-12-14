package com.alkemy.ong.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ActivityRequestDTO {
    @NotEmpty(message = "El nombre es obligatorio")
    private String name;
    @NotEmpty(message = "El contenido es obligatorio")
    private String content;
}
