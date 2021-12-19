package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.response.ActivityUpdateResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ActivityMapper {

    public ActivityUpdateResponseDTO activityUpdateEntity2DTO(ActivityEntity ent) {
        ActivityUpdateResponseDTO dto = new ActivityUpdateResponseDTO();
        dto.setId(ent.getId());
        dto.setName(ent.getName());
        dto.setModifiedBy(ent.getModifiedBy());
        dto.setModifiedDate(ent.getModifiedDate());
        dto.setContent(ent.getContent());
        return dto;
    }
}
