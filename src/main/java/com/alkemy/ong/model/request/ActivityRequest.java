package com.alkemy.ong.model.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;

@Data
@Builder
public class ActivityRequest {
    @NotEmpty(message = "El nombre es obligatorio y no puede ser null")
    @NotNull
    private String name;
    @NotEmpty(message = "El contenido es obligatorio y no puede ser null")
    @NotNull
    private String content;

    private String image;

}