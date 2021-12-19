package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.request.ActivityRequestDTO;
import com.alkemy.ong.model.response.ActivityResponseDTO;
import com.alkemy.ong.model.response.ActivityUpdateResponseDTO;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ActivityMapper {

    public static ActivityEntity activityDTO2Entity(ActivityRequestDTO dto) {
        OffsetDateTime createdDate = OffsetDateTime.now();
        ActivityEntity ent = new ActivityEntity();
        ent.setName(dto.getName());
        ent.setCreatedBy(dto.getCreatedBy());
        ent.setCreatedDate(createdDate);
        ent.setContent(dto.getContent());
        return ent;
    }

    public static ActivityResponseDTO activityEntity2DTO(ActivityEntity ent) {
        ActivityResponseDTO dto = new ActivityResponseDTO();
        dto.setId(ent.getId());
        dto.setName(ent.getName());
        dto.setCreatedBy(ent.getCreatedBy());
        dto.setCreatedDate(ent.getCreatedDate());
        dto.setContent(ent.getContent());
        return dto;
    }

    public ActivityResponseDTO buildToList(ActivityEntity entity) {
        return ActivityResponseDTO.builder()
                .name(entity.getName())
                .build();
    }

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
