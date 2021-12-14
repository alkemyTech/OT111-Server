package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.SlideEntity;
import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

    public SlideEntity slideDTO2Entity(SlideRequestDTO dto) {
        SlideEntity ent = new SlideEntity();
        ent.setImageUrl(dto.getImageUrl());
        ent.setText(dto.getText());
        ent.setOrder(dto.getOrder());
        ent.setOrganization(dto.getOrganization());
        return ent;
    }

    public SlideResponseDTO slideEntity2DTO(SlideEntity ent) {
        SlideResponseDTO dto = new SlideResponseDTO();
        dto.setId(ent.getId());
        dto.setImageUrl(ent.getImageUrl());
        dto.setText(ent.getText());
        dto.setOrder(ent.getOrder());
        dto.setOrganization(ent.getOrganization());
        return dto;
    }

    public SlideResponseDTO buildToList(SlideEntity entity) {
        return SlideResponseDTO.builder()
                .imageUrl(entity.getImageUrl())
                .build();

    }
}
