package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.NewsEntity;
import com.alkemy.ong.model.request.NewsRequestDTO;
import com.alkemy.ong.model.response.news.NewsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class NewsMapper {

    private final CategoryMapper mapper;

    public NewsResponseDTO entity2DTO(NewsEntity entity) {
        if (entity == null){return null;}
        return NewsResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .content(entity.getContent())
                .image(entity.getImage())
                .categoryResponseDTO(mapper.categoryEntity2DTO(entity.getCategoryId()))
                .createdDate(entity.getCreatedDate())
                .createdBy(entity.getCreatedBy())
                .modifiedDate(entity.getModifiedDate())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    public NewsEntity dto2Entity(NewsRequestDTO dto) {
        if (dto == null){return null;}

        NewsEntity entity = new NewsEntity();
        if (dto.getName() != null){
            entity.setName(dto.getName());
        } else {throw new NoSuchElementException("'Nombre' no puede estar vacío.");}
        if (dto.getContent() != null){
            entity.setContent(dto.getContent());
        } else {throw new NoSuchElementException("'Contenido' no puede estar vacío.");}
        if (dto.getImage() != null){
            entity.setImage(dto.getImage());
        } else {throw new NoSuchElementException("'Imagen' no puede estar vacío.");}
        if (dto.getCategoryRequestDTO() != null){
            entity.setCategoryId(mapper.categoryDTO2Entity(dto.getCategoryRequestDTO()));
        } else {throw new NoSuchElementException("'Categoría' no puede estar vacío.");}
            entity.setModifiedBy(dto.getModifiedBy());

        return entity;
    }

    public NewsResponseDTO buildToList(NewsEntity entity){
        return NewsResponseDTO.builder()
                .name(entity.getName())
                .build();
    }
}
