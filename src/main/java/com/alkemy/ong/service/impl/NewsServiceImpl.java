package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.NewsEntity;
import com.alkemy.ong.model.mapper.NewsMapper;
import com.alkemy.ong.model.response.news.NewsDTO;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import lombok.RequiredArgsConstructor;
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
    public void updateNews(NewsDTO newsDTO, Long id) {
        NewsEntity foundNews = newsRepository.findById(id).orElseThrow();
        foundNews.setName(newsDTO.getName());
        foundNews.setContent(newsDTO.getContent());
        foundNews.setImage(newsDTO.getImage());
        newsRepository.save(foundNews);
    }

    @Override
    public void deleteNews(Long id) {
        var foundNews = newsRepository.findById(id).orElseThrow();
        newsRepository.delete(foundNews);
    }

}
