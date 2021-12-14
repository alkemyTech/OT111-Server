package com.alkemy.ong.model.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ActivityRequestDTO {
    @NotEmpty(message = "El nombre es obligatorio y no puede ser null")
    @NotNull
    private String name;
    @NotEmpty(message = "El contenido es obligatorio y no puede ser null")
    @NotNull
    private String content;
}
