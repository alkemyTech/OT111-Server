package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.response.user.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class UserMapper extends AbstractMapper<UserEntity, UserDTO> {


    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDTO entity2DTO(UserEntity entity) {
        if (entity == null) return null;
        return UserDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .photo(entity.getPhoto())
                .role(roleMapper.entity2DTO(entity.getRoleId()))
                .createdDate(entity.getCreatedDate())
                .build();

    }

    @Override
    public UserEntity dto2Entity(UserDTO dto) {
        return null;
    }
}
