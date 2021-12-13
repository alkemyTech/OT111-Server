package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.NewsEntity;
import com.alkemy.ong.model.mapper.NewsMapper;
import com.alkemy.ong.model.response.news.NewsDTO;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    public final NewsRepository newsRepository;
    public final NewsMapper newsMapper;

    public NewsServiceImpl(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }

    @Override
    public NewsDTO findById(Long id){
        NewsEntity toBeFound = newsRepository.findById(id).orElseThrow();
        return newsMapper.entity2DTO(toBeFound);
    }

}
