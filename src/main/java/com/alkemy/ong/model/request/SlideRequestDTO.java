package com.alkemy.ong.model.request;

import com.alkemy.ong.model.entity.OrganizationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SlideRequestDTO {

    @NotEmpty(message = "debe ingresar una url valida")
    private String imageUrl;

    private String text;

    private Integer order;
    private OrganizationEntity organization;
}
