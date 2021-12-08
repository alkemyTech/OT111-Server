package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.RoleEntity;
import com.alkemy.ong.model.response.role.RoleDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleMapper extends AbstractMapper<RoleEntity, RoleDTO>{


    @Override
    public RoleDTO entity2DTO(RoleEntity entity) {
        if (entity == null) return null;

        return RoleDTO.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public RoleEntity dto2Entity(RoleDTO dto) {
        return null;
    }
}
