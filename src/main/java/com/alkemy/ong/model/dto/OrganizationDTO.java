package com.alkemy.ong.model.dto;

import com.alkemy.ong.model.entity.OrganizationEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

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

    public static Map<String, String> smallOrganization(OrganizationEntity organization){
        Map<String, String> response = new HashMap<String,String>();
        response.put("name",organization.getName());
        response.put("image",organization.getImage());
        response.put("phone",Integer.toString(organization.getPhone()));
        response.put("address",organization.getAddress());
        return response;
    }
}
