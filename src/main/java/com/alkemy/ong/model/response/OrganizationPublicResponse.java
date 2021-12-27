package com.alkemy.ong.model.response;

import com.alkemy.ong.model.entity.OrganizationEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

}
