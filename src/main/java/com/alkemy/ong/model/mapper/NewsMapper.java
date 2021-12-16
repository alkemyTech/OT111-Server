package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.NewsEntity;
import com.alkemy.ong.model.response.news.NewsDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class NewsMapper extends AbstractMapper<NewsEntity, NewsDTO> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public NewsDTO entity2DTO(NewsEntity entity) {
        if (entity == null) return null;

        return NewsDTO.builder()
                .name(entity.getName())
                .content(entity.getContent())
                .image(entity.getImage())
                .categoryResponseDTO(categoryMapper.categoryEntity2DTO(entity.getCategoryId()))
                .build();
    }

    @Override
    public NewsEntity dto2Entity(NewsDTO dto) {
        return null;
    }

}
