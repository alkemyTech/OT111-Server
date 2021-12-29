package com.alkemy.ong.utils;

import com.alkemy.ong.model.entity.OrganizationEntity;
import com.alkemy.ong.model.entity.SlideEntity;
import com.alkemy.ong.model.request.OrganizationRequest;

public class OrganizationMocks {

    public static OrganizationEntity buildOrganizationEntity() {
        return OrganizationEntity.builder()
                .name("Mock Organization")
                .image("MockImage.jpg")
                .address("Mock address")
                .phone(999999)
                .email("mock@mock.org")
                .welcomeText("Mock welcome Text")
                .aboutUsText("Mock about us text")
                .facebookUrl("Mock facebook url")
                .instagramUrl("Mock instagram url")
                .linkedinUrl("Mock linkedin url")
                .build();
    }

    public static SlideEntity buildSlidesEntityWithOrganizationId(OrganizationEntity org) {
        return SlideEntity.builder()
                .imageUrl("MockImage.jpg")
                .text("Texto")
                .order(2)
                .organization(org)
                .build();
    }

    public static OrganizationRequest buildOrganizationRequest() {
        return OrganizationRequest.builder()
                .name("Mock Organization")
                .image("MockImage.jpg")
                .address("Mock address")
                .phone(999999)
                .email("mock@mock.org")
                .welcomeText("Mock welcome Text")
                .aboutUsText("Mock about us text")
                .facebookUrl("Mock facebook url")
                .instagramUrl("Mock instagram url")
                .linkedinUrl("Mock linkedin url")
                .build();
    }

    public static OrganizationRequest buildOrganizationRequestInvalidFields() {
        return OrganizationRequest.builder()
                .name("")
                .image("")
                .address("Mock address")
                .phone(999999)
                .email("")
                .welcomeText("")
                .aboutUsText("Mock about us text")
                .facebookUrl("Mock facebook url")
                .instagramUrl("Mock instagram url")
                .linkedinUrl("Mock linkedin url")
                .build();
    }

}
