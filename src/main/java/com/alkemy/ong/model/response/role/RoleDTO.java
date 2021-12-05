package com.alkemy.ong.model.response.role;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class RoleDTO {

    private String name;

    private String description;

}
