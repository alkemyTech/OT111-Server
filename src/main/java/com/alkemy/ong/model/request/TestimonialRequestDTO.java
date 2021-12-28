package com.alkemy.ong.model.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class TestimonialRequestDTO {
    @NotEmpty(message = "Name is mandatory")
    private String name;

    private String image;

    @NotEmpty(message = "content is mandatory")
    private String content;
}
