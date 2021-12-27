package com.alkemy.ong.utils;

import com.alkemy.ong.model.entity.MemberEntity;
import com.alkemy.ong.model.request.MemberRequest;

public class MemberMocks {

    public static MemberEntity buildMemberEntity() {
        return MemberEntity.builder()
                .name("Mock Member")
                .image("MockMemberImage.jpg")
                .description("Mock Member Description")
                .facebookUrl("Mock facebook url")
                .instagramUrl("Mock instagram url")
                .linkedinUrl("Mock linkedin url")
                .build();
    }

    public static MemberRequest buildMemberRequest() {
        return MemberRequest.builder()
                .name("Name mock")
                .image("image mock")
                .description("Description mock")
                .facebookUrl("facebook url mock")
                .instagramUrl("instagram url mock")
                .linkedinUrl("linkedin url mock")
                .build();
    }

    public static MemberRequest buildMemberRequestInvalidFields() {
        return MemberRequest.builder()
                .name("")
                .image("")
                .description("Description mock")
                .facebookUrl("facebook url mock")
                .instagramUrl("instagram url mock")
                .linkedinUrl("linkedin url mock")
                .build();
    }
}
