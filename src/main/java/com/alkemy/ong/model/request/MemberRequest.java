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

    public static MemberEntity toEntity(MemberRequest request) {

        return MemberEntity.builder()
                .name(request.getName())
                .image(request.getImage())
                .description(request.getDescription())
                .instagramUrl(request.getInstagramUrl())
                .facebookUrl(request.getFacebookUrl())
                .linkedinUrl(request.getLinkedinUrl())
                .build();
    }
}
