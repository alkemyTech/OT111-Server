package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.request.ActivityRequest;
import com.alkemy.ong.model.response.ActivityResponseDTO;
import com.alkemy.ong.model.response.ActivityUpdateResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@Builder
public class ActivityMapper {

    public static ActivityEntity activityDTO2Entity(ActivityRequest dto, String userName) {
        OffsetDateTime createdDate = OffsetDateTime.now();
//        ActivityEntity ent = new ActivityEntity();
//        ent.setName(dto.getName());
//        ent.setCreatedBy(userName);
//        ent.setCreatedDate(createdDate);
//        ent.setContent(dto.getContent());
//        return ent;
        return ActivityEntity.builder()
                .name(dto.getName())
                .content(dto.getContent())
                .image(dto.getImage())
//                .createdBy(userName)
//                .createdDate(createdDate)
                .build();
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
