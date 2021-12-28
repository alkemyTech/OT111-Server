package com.alkemy.ong.model.mapper;


import com.alkemy.ong.model.entity.OrganizationEntity;
import com.alkemy.ong.model.entity.SlideEntity;
import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.OrganizationPublicResponse;
import com.alkemy.ong.model.response.SlideListadoResponseDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

    public SlideEntity toEntity(SlideRequestDTO dto, OrganizationEntity org, String awsImage) {
        SlideEntity ent = new SlideEntity();
        ent.setImageUrl(awsImage);
        ent.setText(dto.getText());
        ent.setOrder(dto.getOrder());
        ent.setOrganization(org);
        return ent;
    }

    public SlideResponseDTO toDTO(SlideEntity ent) {
        SlideResponseDTO dto = new SlideResponseDTO();
        dto.setId(ent.getId());
        dto.setImageUrl(ent.getImageUrl());
        dto.setText(ent.getText());
        dto.setOrder(ent.getOrder());
        dto.setOrganization(OrganizationPublicResponse.toDTO(ent.getOrganization()));
        return dto;
    }

    public SlideResponseDTO toDTONoOrg(SlideEntity ent) {
        SlideResponseDTO dto = new SlideResponseDTO();
        dto.setId(ent.getId());
        dto.setImageUrl(ent.getImageUrl());
        dto.setText(ent.getText());
        dto.setOrder(ent.getOrder());
        return dto;
    }

    public SlideListadoResponseDTO toDtoListado(SlideEntity ent) {
        return SlideListadoResponseDTO.builder()
                .imageUrl(ent.getImageUrl())
                .order(ent.getOrder())
                .build();
    }



}
