package com.alkemy.ong.service;

import com.alkemy.ong.model.response.news.NewsDTO;


public interface NewsService {
    NewsDTO findById(Long id);

    void updateNews(NewsDTO newsDTO, Long id);

    void deleteNews(Long id);

}
