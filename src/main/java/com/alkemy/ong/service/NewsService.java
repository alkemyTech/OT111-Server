package com.alkemy.ong.service;

import com.alkemy.ong.model.response.news.NewsDTO;

import java.util.List;


public interface NewsService {
    NewsDTO findById(Long id);

    List<NewsDTO> getNews();
}
