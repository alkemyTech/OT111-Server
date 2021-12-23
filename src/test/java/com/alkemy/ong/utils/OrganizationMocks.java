package com.alkemy.ong.utils;

import com.alkemy.ong.model.entity.OrganizationEntity;
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

    public static OrganizationRequest buildOrganizationRequestInvalidName() {
        return OrganizationRequest.builder()
                .name("")
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

    public static OrganizationRequest buildOrganizationRequestInvalidImage() {
        return OrganizationRequest.builder()
                .name("Mock Organization")
                .image("")
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

    public static OrganizationRequest buildOrganizationRequestInvalidEmail() {
        return OrganizationRequest.builder()
                .name("Mock Organization")
                .image("MockImage.jpg")
                .address("Mock address")
                .phone(999999)
                .email("")
                .welcomeText("Mock welcome Text")
                .aboutUsText("Mock about us text")
                .facebookUrl("Mock facebook url")
                .instagramUrl("Mock instagram url")
                .linkedinUrl("Mock linkedin url")
                .build();
    }

    public static OrganizationRequest buildOrganizationRequestInvalidWelcomeText() {
        return OrganizationRequest.builder()
                .name("Mock Organization")
                .image("MockImage.jpg")
                .address("Mock address")
                .phone(999999)
                .email("mock@mock.org")
                .welcomeText("")
                .aboutUsText("Mock about us text")
                .facebookUrl("Mock facebook url")
                .instagramUrl("Mock instagram url")
                .linkedinUrl("Mock linkedin url")
                .build();
    }

}
