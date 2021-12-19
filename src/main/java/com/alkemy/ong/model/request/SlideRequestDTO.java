package com.alkemy.ong.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Base64;

@Getter
@Setter
public class SlideRequestDTO {

   @NotNull
    private Base64 imagenCodificada;

    private String text;

    private Integer order;

    @NotNull
    private long organizationId;


}
