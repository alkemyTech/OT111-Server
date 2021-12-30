package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.request.ActivityRequest;
import com.alkemy.ong.model.response.ActivityResponseDTO;
import lombok.Builder;
import org.springframework.stereotype.Service;


@Service
@Builder
public class ActivityMapper {

    public static ActivityEntity activityDTO2Entity(ActivityRequest dto) {
        return ActivityEntity.builder()
                .name(dto.getName())
                .content(dto.getContent())
                .image(dto.getImage())
                .build();
    }

    public static ActivityResponseDTO activityEntity2DTO(ActivityEntity ent) {
        ActivityResponseDTO dto = new ActivityResponseDTO();
        dto.setId(ent.getId());
        dto.setName(ent.getName());
        dto.setContent(ent.getContent());
        return dto;
    }
}
