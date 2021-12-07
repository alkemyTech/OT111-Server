package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponseDTO categoryEntity2DTO(CategoryEntity ent){
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setName(ent.getName());
        dto.setDescription(ent.getDescription());
        dto.setImage(ent.getImage());
        return dto;
    }
}