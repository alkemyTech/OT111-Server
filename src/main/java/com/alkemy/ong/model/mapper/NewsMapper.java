package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.entity.NewsEntity;
import com.alkemy.ong.model.request.NewsRequestDTO;
import com.alkemy.ong.model.response.news.NewsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsMapper extends AbstractMapper<NewsEntity, NewsResponseDTO> {

    private final CategoryMapper categoryMapper;


    public NewsResponseDTO toDto(NewsEntity entity, CategoryEntity categoryEntity) {
        if (entity == null) return null;
        return NewsResponseDTO.builder()
                .name(entity.getName())
                .content(entity.getContent())
                .image(entity.getImage())
                .category(categoryMapper.toDTO(categoryEntity))
                .build();
    }

    public NewsEntity toEntity(NewsRequestDTO dto, CategoryEntity categoryEntity) {
        if (dto == null) return null;
        return NewsEntity.builder()
                .name(dto.getName())
                .content(dto.getContent())
                .image(dto.getImage())
                .category(categoryEntity)
                .build();
    }

    @Override
    public NewsResponseDTO entity2DTO(NewsEntity entity) {
        if (entity == null) return null;

        return NewsResponseDTO.builder()
                .name(entity.getName())
                .content(entity.getContent())
                .image(entity.getImage())
                .category(categoryMapper.toDTO(entity.getCategory()))
                .build();
    }

    @Override
    public NewsEntity dto2Entity(NewsResponseDTO dto) {
        return null;
    }

}

