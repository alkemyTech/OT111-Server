package com.alkemy.ong.model.request;

import com.alkemy.ong.model.entity.MemberEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequest {

    @NotEmpty(message = "El nombre es obligatorio")
    private String name;

    private String facebookUrl;

    private String instagramUrl;

    private String linkedinUrl;

    @NotEmpty(message = "La imagen es obligatoria")
    private String image;

    private String description;

    public MemberEntity toEntity() {

        return MemberEntity.builder()
                .name(name)
                .image(image)
                .description(description)
                .instagramUrl(instagramUrl)
                .facebookUrl(facebookUrl)
                .linkedinUrl(linkedinUrl)
                .build();
    }

    public static MemberEntity refreshData(MemberEntity memberEntity, MemberRequest memberRequest) {
        memberEntity.setName(memberRequest.getName());
        memberEntity.setImage(memberRequest.getImage());
        memberEntity.setDescription(memberRequest.getDescription());
        memberEntity.setInstagramUrl(memberRequest.getInstagramUrl());
        memberEntity.setFacebookUrl(memberRequest.getFacebookUrl());
        memberEntity.setLinkedinUrl(memberRequest.getLinkedinUrl());
        return memberEntity;

    }
}
