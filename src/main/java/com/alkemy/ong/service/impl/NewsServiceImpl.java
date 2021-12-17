package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.NewsEntity;
import com.alkemy.ong.model.mapper.NewsMapper;
import com.alkemy.ong.model.response.news.NewsDTO;
import com.alkemy.ong.model.response.pagination.CustomPage;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Override
    public NewsDTO findById(Long id) {
        NewsEntity toBeFound = newsRepository.findById(id).orElseThrow();
        return newsMapper.entity2DTO(toBeFound);
    }

    @Override
    public CustomPage<NewsDTO> getNewsPageable(Pageable pageRequest) {
        return new CustomPage<>(newsRepository.findAll(pageRequest)
                .map(newsMapper::entity2DTO));
    }


}
