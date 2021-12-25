package com.alkemy.ong.model.response;

import com.alkemy.ong.model.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberResponse {

    private Long id;

    private String name;

    private String facebookUrl;

    private String instagramUrl;

    private String linkedinUrl;

    private String image;

    private String description;

    public static MemberResponse toDTO(MemberEntity entity) {
        return MemberResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .description(entity.getDescription())
                .facebookUrl(entity.getFacebookUrl())
                .instagramUrl(entity.getInstagramUrl())
                .linkedinUrl(entity.getLinkedinUrl())
                .build();
    }
    
}
