package com.alkemy.ong.model.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;

@Getter
@Setter
public class ActivityRequestDTO {
    @NotEmpty(message = "El nombre es obligatorio y no puede ser null")
    @NotNull
    private String name;
    @NotEmpty(message = "El contenido es obligatorio y no puede ser null")
    @NotNull
    private String content;
    @NotEmpty(message = "La fecha de creacion es obligatoria y no puede ser null")
    @NotNull
    private OffsetDateTime createdDate;
    @NotEmpty(message = "La fecha de creacion es obligatoria y no puede ser null")
    @NotNull
    private String createdBy;

}
