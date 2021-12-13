package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.CategoryEntity;
<<<<<<< HEAD
import com.alkemy.ong.model.response.category.CategoryDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CategoryMapper extends AbstractMapper <CategoryEntity, CategoryDTO> {


    @Override
    public CategoryDTO entity2DTO(CategoryEntity entity) {
        if (entity == null) return null;

        return CategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .image(entity.getImage())
                .build();
    }

    @Override
    public CategoryEntity dto2Entity(CategoryDTO dto) {return null;}
=======
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

>>>>>>> develop
}
