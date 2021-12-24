package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper extends AbstractMapper<UserEntity, UserResponse> {

    private final RoleMapper roleMapper;

    @Override
    public UserResponse entity2DTO(UserEntity entity) {
        if (entity == null) return null;
        return UserResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .photo(entity.getPhoto())
                .roles(roleMapper.entity2DTO(entity.getRoles()))
         //       .createdDate(entity.getCreatedDate())
                .build();

    }

    @Override
    public UserEntity dto2Entity(UserResponse dto) {
        return null;
    }

}