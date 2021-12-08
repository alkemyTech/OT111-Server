package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.NewsEntity;
import com.alkemy.ong.model.response.News.NewsDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
public class NewsMapper extends AbstractMapper <NewsEntity, NewsDTO> {


    @Override
    public NewsDTO entity2DTO(NewsEntity entity) {
        if (entity == null) return null;

        return NewsDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .content(entity.getContent())
                .image(entity.getImage())
                //.categoryId(entity.getCategoryId())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .createdBy(entity.getCreatedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    @Override
    public NewsEntity dto2Entity(NewsDTO dto) {return null;}

}
