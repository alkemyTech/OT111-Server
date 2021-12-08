package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.CategoryEntity;
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
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .createdBy(entity.getCreatedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    @Override
    public CategoryEntity dto2Entity(CategoryDTO dto) {return null;}
}
