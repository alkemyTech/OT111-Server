package com.alkemy.ong.model.response;

import com.alkemy.ong.model.entity.AuditableEntity;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.mapper.RoleMapper;
import com.alkemy.ong.model.mapper.UserMapper;
import com.alkemy.ong.model.response.role.RoleDTO;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse extends AuditableEntity {

    private final RoleMapper roleMapper;

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

    private List<RoleDTO> roles;

    public UserResponse toDTO(UserEntity entity) {
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
}
