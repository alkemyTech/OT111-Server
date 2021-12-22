package com.alkemy.ong.model.response;

import com.alkemy.ong.model.entity.OrganizationEntity;
import com.alkemy.ong.model.request.OrganizationRequest;
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

    public static OrganizationEntity refreshData(OrganizationEntity organizationEntity, OrganizationRequest organizationRequest) {
        organizationEntity.setName(organizationRequest.getName());
        organizationEntity.setImage(organizationRequest.getImage());
        organizationEntity.setAddress(organizationRequest.getAddress());
        organizationEntity.setPhone(organizationRequest.getPhone());
        organizationEntity.setEmail(organizationRequest.getEmail());
        organizationEntity.setWelcomeText(organizationRequest.getWelcomeText());
        organizationEntity.setAboutUsText(organizationRequest.getAboutUsText());
        organizationEntity.setInstagramUrl(organizationRequest.getInstagramUrl());
        organizationEntity.setFacebookUrl(organizationRequest.getFacebookUrl());
        organizationEntity.setLinkedinUrl(organizationRequest.getLinkedinUrl());
        return organizationEntity;
    }

    public static OrganizationEntity toEntity(OrganizationRequest request) {

        return OrganizationEntity.builder()
                .name(request.getName())
                .image(request.getImage())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .welcomeText(request.getWelcomeText())
                .aboutUsText(request.getAboutUsText())
                .instagramUrl(request.getInstagramUrl())
                .facebookUrl(request.getFacebookUrl())
                .linkedinUrl(request.getLinkedinUrl())
                .build();
    }
}

