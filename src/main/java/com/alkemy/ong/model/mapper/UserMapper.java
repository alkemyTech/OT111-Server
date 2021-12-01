package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.dto.UserDTO;
import com.alkemy.ong.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    //
    // === DTO -> Entity ===
    public UserEntity userDTO2Entity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    //
    // === Entity -> DTO ===
    public UserDTO userEntity2DTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        return dto;
    }

}
