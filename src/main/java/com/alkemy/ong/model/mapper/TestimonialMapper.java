package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.entity.TestimonialEntity;
import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.request.TestimonialRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.model.response.TestimonialResponseDTO;

public class TestimonialMapper {

    public TestimonialEntity testimonialDTO2Entity(TestimonialRequestDTO dto) {
        TestimonialEntity ent = new TestimonialEntity();
        ent.setName(dto.getName());
        ent.setImage(dto.getImage());
        ent.setContent(dto.getContent());
        return ent;
    }

    public TestimonialResponseDTO testimonialEntity2DTO(TestimonialEntity ent) {
        TestimonialResponseDTO dto = new TestimonialResponseDTO();
        dto.setId(ent.getId());
        dto.setName(ent.getName());
        dto.setContent(ent.getContent());
        dto.setImage(ent.getImage());
        return dto;
    }
    /*
    public CategoryResponseDTO buildToList(CategoryEntity entity) {
        return CategoryResponseDTO.builder()
                .name(entity.getName())
                .build();
    }*/
}
