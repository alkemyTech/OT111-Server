package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.dto.OrganizationDTO;
import com.alkemy.ong.model.entity.OrganizationEntity;
import com.alkemy.ong.model.entity.SlideEntity;
import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

    public SlideEntity slideDTO2Entity(SlideRequestDTO dto, OrganizationEntity org, String awsImage) {
        SlideEntity ent = new SlideEntity();
        ent.setImageUrl(awsImage);
        ent.setText(dto.getText());
        ent.setOrder(dto.getOrder());
        ent.setOrganization(org);
        return ent;
    }

    public SlideResponseDTO slideEntity2DTO(SlideEntity ent) {
        SlideResponseDTO dto = new SlideResponseDTO();
        dto.setId(ent.getId());
        dto.setImageUrl(ent.getImageUrl());
        dto.setText(ent.getText());
        dto.setOrder(ent.getOrder());
        dto.setOrganization(OrganizationDTO.builder() //TODO: usar el mapper de organization
                        .name(ent.getOrganization().getName())
                        .image(ent.getOrganization().getImage())
                        .address(ent.getOrganization().getAddress())
                        .phone(ent.getOrganization().getPhone())
                        .email(ent.getOrganization().getEmail())
                        .aboutUsText(ent.getOrganization().getAboutUsText())
                        .welcomeText(ent.getOrganization().getWelcomeText())
                .build());
        return dto;
    }

    public SlideResponseDTO buildToList(SlideEntity entity){
        return SlideResponseDTO.builder().imageUrl(String.valueOf(entity.getClass())).build();
    }

}
