package com.alkemy.ong.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
