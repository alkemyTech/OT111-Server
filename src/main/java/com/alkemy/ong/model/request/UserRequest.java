package com.alkemy.ong.model.request;


import com.alkemy.ong.model.entity.AuditableEntity;
import com.alkemy.ong.model.entity.RoleEntity;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.repository.RoleRepository;
import lombok.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest extends AuditableEntity {

    private static RoleRepository roleRepository;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String email;

    @NotEmpty
    private String photo;

    @NotEmpty
    private List<Integer> roles;

    public static UserEntity updateEntity(UserRequest request, UserEntity foundUser,List<RoleEntity> role){

        foundUser.setFirstName(request.getFirstName());
        foundUser.setLastName(request.getLastName());
        foundUser.setEmail(request.getEmail());
        foundUser.setPhoto(request.getPhoto());
        foundUser.setRoles(role);

        return foundUser;
    };

}
