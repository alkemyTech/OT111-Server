package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.NewsEntity;
import com.alkemy.ong.model.mapper.NewsMapper;
import com.alkemy.ong.model.request.NewsRequestDTO;
import com.alkemy.ong.model.response.news.NewsResponseDTO;
import com.alkemy.ong.model.response.pagination.CustomPage;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final NewsMapper newsMapper;

    @Override
    public NewsResponseDTO findNewsById(Long id) {
        NewsEntity toBeFound = newsRepository.findById(id).orElseThrow();
        return newsMapper.entity2DTO(toBeFound);
    }

    @Override
    public CustomPage<NewsResponseDTO> getNewsPageable(Pageable pageRequest) {
        return new CustomPage<>(newsRepository.findAll(pageRequest)
                .map(newsMapper::entity2DTO));
    }

    @Override
    public NewsResponseDTO updateNews(NewsRequestDTO newsRequestDTO, Long id) {
        var foundNewsEntity = newsRepository.findById(id).orElseThrow();
        var foundCategoryEntity = categoryRepository.findById(newsRequestDTO.getCategoryId()).orElseThrow();

        foundNewsEntity.setName(newsRequestDTO.getName());
        foundNewsEntity.setContent(newsRequestDTO.getContent());
        foundNewsEntity.setImage(newsRequestDTO.getImage());
        foundNewsEntity.setCategory(foundCategoryEntity);

        return newsMapper.entity2DTO(newsRepository.save(foundNewsEntity));
    }

    @Override
    public NewsResponseDTO saveNews(NewsRequestDTO newsRequestDTO) {
        var foundCategory = categoryRepository.findById(newsRequestDTO.getCategoryId()).orElseThrow();
        NewsEntity newNews = newsMapper.toEntity(newsRequestDTO, foundCategory);
        NewsEntity savedNews = newsRepository.save(newNews);
        return newsMapper.toDto(savedNews, foundCategory);
    }

    public void deleteNews(Long id) {
        var foundNews = newsRepository.findById(id).orElseThrow();
        newsRepository.delete(foundNews);
    }

}
