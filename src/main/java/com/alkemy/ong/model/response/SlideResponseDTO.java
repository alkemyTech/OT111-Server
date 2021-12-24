package com.alkemy.ong.model.response;

import com.alkemy.ong.model.dto.OrganizationDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlideResponseDTO {
    private Long id;

    private String imageUrl;

    private String text;

    private Integer order;

    private OrganizationDTO organization;

}
