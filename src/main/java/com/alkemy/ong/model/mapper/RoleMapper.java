package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.RoleEntity;
import com.alkemy.ong.model.response.role.RoleResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleMapper extends AbstractMapper<RoleEntity, RoleResponseDTO>{


    @Override
    public RoleResponseDTO entity2DTO(RoleEntity entity) {
        if (entity == null) return null;

        return RoleResponseDTO.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public RoleEntity dto2Entity(RoleResponseDTO dto) {
        return null;
    }

}
