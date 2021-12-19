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
    public NewsResponseDTO findById(Long id) {
        NewsEntity toBeFound = newsRepository.findById(id).orElseThrow();
        return newsMapper.entity2DTO(toBeFound);
    }

    @Override
    public CustomPage<NewsResponseDTO> getNewsPageable(Pageable pageRequest) {
        return new CustomPage<>(newsRepository.findAll(pageRequest)
                .map(newsMapper::entity2DTO));
    }


    public void updateNews(NewsResponseDTO newsResponseDTO, Long id) {
        NewsEntity foundNews = newsRepository.findById(id).orElseThrow();
        foundNews.setName(newsResponseDTO.getName());
        foundNews.setContent(newsResponseDTO.getContent());
        foundNews.setImage(newsResponseDTO.getImage());
        newsRepository.save(foundNews);
    }

    @Override
    public NewsResponseDTO saveNews(NewsRequestDTO request) {
        var foundCategory = categoryRepository.findById(request.getCategoryId()).orElseThrow();
        NewsEntity newNews = newsMapper.dtoRequest2Entity(request, foundCategory);
        NewsEntity savedNews = newsRepository.save(newNews);
        return newsMapper.entity2DTOResponse(savedNews, foundCategory);
    }

    public void deleteNews(Long id) {
        var foundNews = newsRepository.findById(id).orElseThrow();
        newsRepository.delete(foundNews);
    }

}
