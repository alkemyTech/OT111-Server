package com.alkemy.ong.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationDTO {
    private String name;
    private String image;
    private String address;
    private int phone;
    private String email;
    private String welcomeText;
    private String aboutUsText;


}
