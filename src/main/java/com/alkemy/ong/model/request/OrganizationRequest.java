package com.alkemy.ong.model.request;

import com.alkemy.ong.model.entity.OrganizationEntity;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationRequest {

    @NotEmpty(message = "El nombre es obligatorio")
    private String name;

    @NotEmpty(message = "La imagen es obligatorio")
    private String image;

    private String address;

    private Integer phone;

    @Email(message = "El email es obligatorio")
    @NotEmpty
    private String email;

    @NotEmpty(message = "El texto de bienvenida es obligatorio")
    private String welcomeText;

    private String aboutUsText;

    private String facebookUrl;

    private String instagramUrl;

    private String linkedinUrl;

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
