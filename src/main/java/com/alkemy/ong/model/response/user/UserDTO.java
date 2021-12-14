package com.alkemy.ong.model.response.user;

import com.alkemy.ong.model.response.role.RoleDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@Data
public class UserDTO {

    private Long id; //Check if needed.

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

    private List<RoleDTO> roles;

    private OffsetDateTime createdDate;

    private String createdBy;

    private OffsetDateTime modifiedDate;

    private String modifiedBy;

}
