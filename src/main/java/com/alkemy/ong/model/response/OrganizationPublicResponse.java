package com.alkemy.ong.model.response;

import com.alkemy.ong.model.entity.OrganizationEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrganizationPublicResponse {

    private String name;

    private String image;

    private String address;

    private Integer phone;

    private String facebookUrl;

    private String instagramUrl;

    private String linkedinUrl;

    private List<SlideResponseDTO> slides;

    public static OrganizationPublicResponse toDTO(OrganizationEntity entity) {
        return OrganizationPublicResponse.builder()
                .name(entity.getName())
                .image(entity.getImage())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .facebookUrl(entity.getFacebookUrl())
                .instagramUrl(entity.getInstagramUrl())
                .linkedinUrl(entity.getLinkedinUrl())
                .build();
    }

    public static OrganizationPublicResponse toDTO(OrganizationEntity entity, List<SlideResponseDTO> slidesDto) {
        return OrganizationPublicResponse.builder()
                .name(entity.getName())
                .image(entity.getImage())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .facebookUrl(entity.getFacebookUrl())
                .instagramUrl(entity.getInstagramUrl())
                .linkedinUrl(entity.getLinkedinUrl())
                .slides(slidesDto)
                .build();
    }

}
