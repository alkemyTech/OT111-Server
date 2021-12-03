package com.alkemy.ong.model.response.user;

import com.alkemy.ong.model.response.role.RoleDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {


    private Long id; //Check if needed.

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

    private RoleDTO role;

    private String createdDate;

    private String createdBy;

    private String modifiedDate;

    private String modifiedBy;

}
