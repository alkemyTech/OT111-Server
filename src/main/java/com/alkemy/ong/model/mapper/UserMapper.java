package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.response.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper extends AbstractMapper<UserEntity, UserResponseDTO> {

    private final RoleMapper roleMapper;

    @Override
    public UserResponseDTO entity2DTO(UserEntity entity) {
        if (entity == null) return null;
        return UserResponseDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .photo(entity.getPhoto())
                .roles(roleMapper.entity2DTO(entity.getRoles()))
                .createdDate(entity.getCreatedDate())
                .build();

    }

    @Override
    public UserEntity dto2Entity(UserResponseDTO dto) {
        return null;
    }

    /*public UserRequestDTO entityUpdate2DTO(UserEntity entity) {
        if (entity == null) return null;
        return UserRequestDTO.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .photo(entity.getPhoto())
                .roles(roleMapper.entity2DTO(entity.getRoles()))
                .build();

    }*/

    /*public UserEntity updateDTO2Entity(UserRequestDTO userUpdateDTO) {
        return null;
    }*/
}