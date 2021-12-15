package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryEntity categoryDTO2Entity(CategoryRequestDTO dto) {
        CategoryEntity ent = new CategoryEntity();
        ent.setName(dto.getName());
        ent.setDescription(dto.getDescription());
        ent.setImage(dto.getImage());
        return ent;
    }

    public CategoryResponseDTO categoryEntity2DTO(CategoryEntity ent) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        System.out.println(ent);
        dto.setId(ent.getId());
        dto.setName(ent.getName());
        dto.setDescription(ent.getDescription());
        dto.setImage(ent.getImage());
        return dto;
    }

    public CategoryResponseDTO buildToList(CategoryEntity entity) {
        return CategoryResponseDTO.builder()
                .name(entity.getName())
                .build();
    }

}
