package com.alkemy.ong.model.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SlideRequestDTO {

    @NotEmpty
    private String imagenCodificada;

    private String text;

    private Integer order;

    @NotNull
    private long organizationId;
}
