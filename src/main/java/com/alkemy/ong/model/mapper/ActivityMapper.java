package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.request.ActivityRequestDTO;
import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.ActivityResponseDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;

public class ActivityMapper {

    public ActivityEntity activityDTO2Entity(ActivityRequestDTO dto) {
        ActivityEntity ent = new ActivityEntity();
        ent.setName(dto.getName());
        ent.setContent(dto.getContent());
        return ent;
    }

    public ActivityResponseDTO ActivityEntity2DTO(ActivityEntity ent) {
        ActivityResponseDTO dto = new ActivityResponseDTO();
        dto.setId(ent.getId());
        dto.setName(ent.getName());
        dto.setContent(ent.getContent());
        return dto;
    }

    public ActivityResponseDTO buildToList(ActivityEntity entity) {
        return ActivityResponseDTO.builder()
                .name(entity.getName())
                .build();
    }
}
