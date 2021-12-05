package com.alkemy.ong.model.request.user;

import com.alkemy.ong.model.response.role.RoleResponseDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserUpdateDTO {


    private Long id; //Check if needed.

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String photo;

    private RoleResponseDTO role;

    private String modifiedDate;

    private String modifiedBy;

}
