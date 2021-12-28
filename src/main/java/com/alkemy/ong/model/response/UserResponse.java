package com.alkemy.ong.model.response;

import com.alkemy.ong.model.entity.AuditableEntity;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.mapper.RoleMapper;
import com.alkemy.ong.model.response.role.RoleDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {

    private static final RoleMapper roleMapper = new RoleMapper();

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

    private List<RoleDTO> roles;

    public static UserResponse toDTO(UserEntity entity) {
        if (entity == null) return null;

        return UserResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .photo(entity.getPhoto())
                .roles(roleMapper.entity2DTO(entity.getRoles()))
                .build();

    }

    public static List<UserResponse> listToDTO(List<UserEntity> users) {

        List<UserResponse> result = new ArrayList<>();

        for (UserEntity temp : users) {

            UserResponse obj = new UserResponse();
            obj.setId(temp.getId());
            obj.setFirstName(temp.getFirstName());
            obj.setLastName(temp.getLastName());
            obj.setEmail(temp.getEmail());
            obj.setPhoto(temp.getPhoto());
            obj.setRoles(roleMapper.entity2DTO(temp.getRoles()));

            result.add(obj);
        }

        return result;

    }
}
