package com.alkemy.ong.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestimonialResponseDTO {
    private Long id;
    private String name;
    private String image;
    private String content;
}
