package com.alkemy.ong.model.response;

import com.alkemy.ong.model.entity.OrganizationEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrganizationFullResponse {

    private Long id;

    private String name;

    private String image;

    private String address;

    private Integer phone;

    private String email;

    private String welcomeText;

    private String aboutUsText;

    private String facebookUrl;

    private String instagramUrl;

    private String linkedinUrl;

    public static OrganizationFullResponse toDTO(OrganizationEntity entity) {
        return OrganizationFullResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .welcomeText(entity.getWelcomeText())
                .aboutUsText(entity.getAboutUsText())
                .facebookUrl(entity.getFacebookUrl())
                .instagramUrl(entity.getInstagramUrl())
                .linkedinUrl(entity.getLinkedinUrl())
                .build();
    }

}

