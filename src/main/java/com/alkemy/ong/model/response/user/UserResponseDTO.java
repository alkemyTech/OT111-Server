package com.alkemy.ong.model.response.user;

import com.alkemy.ong.model.response.role.RoleResponseDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponseDTO {


    private Long id; //Check if needed.

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

    private RoleResponseDTO role;

    private String createdDate;

    private String createdBy;

    private String modifiedDate;

    private String modifiedBy;

}
