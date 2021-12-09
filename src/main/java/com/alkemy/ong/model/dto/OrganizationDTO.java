package com.alkemy.ong.model.dto;

import com.alkemy.ong.model.entity.OrganizationEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrganizationDTO {

    private String name;
    private String image;
    private String address;
    private int phone;
    private String email;
    private String welcomeText;
    private String aboutUsText;

    public static OrganizationDTO buildPublicData(OrganizationEntity organization) {
        return OrganizationDTO.builder()
                .name(organization.getName())
                .image(organization.getImage())
                .phone(organization.getPhone())
                .address(organization.getAddress())
                .build();
    }

}
