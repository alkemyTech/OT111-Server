package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.entity.NewsEntity;
import com.alkemy.ong.model.mapper.CategoryMapper;
import com.alkemy.ong.model.mapper.NewsMapper;
import com.alkemy.ong.model.request.NewsRequestDTO;
import com.alkemy.ong.model.response.news.NewsResponseDTO;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final NewsMapper newsMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public NewsResponseDTO saveNews(NewsRequestDTO request) {
        NewsEntity newsEntity = newsMapper.dto2Entity(request);
        CategoryEntity categoryEntity;
        categoryEntity = categoryMapper.categoryDTO2Entity(request.getCategoryRequestDTO());
        newsEntity.setCategoryId(categoryRepository.getById(categoryEntity.getId()));
        NewsEntity newsSaved = newsRepository.save(newsEntity);
        return newsMapper.entity2DTO(newsSaved);
    }
}
