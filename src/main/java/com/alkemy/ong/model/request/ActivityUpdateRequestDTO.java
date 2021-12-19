package com.alkemy.ong.model.request;

import lombok.Data;

@Data
public class ActivityUpdateRequestDTO {

    private Long id;

    private String name;

    private String content;

    private String image;
}
