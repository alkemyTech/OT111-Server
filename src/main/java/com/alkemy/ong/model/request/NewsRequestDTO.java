package com.alkemy.ong.model.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class NewsRequestDTO {

    @NotEmpty(message = "Name is mandatory")
    private String name;

    @NotEmpty(message = "Content is mandatory")
    private String content;

    private String image;

    @NotNull(message = "Category ID is mandatory")
    private Long categoryId;
}
