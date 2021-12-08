package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.NewsEntity;
import com.alkemy.ong.model.mapper.NewsMapper;
import com.alkemy.ong.model.response.news.NewsDTO;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    public NewsDTO findById(Long id){
        Optional <NewsEntity> toBeFound = newsRepository.findById(id);
        if (!toBeFound.isPresent()){
            throw new IllegalArgumentException("No news for id " + id);
        }
        return newsMapper.entity2DTO(toBeFound.get());
    }

}
