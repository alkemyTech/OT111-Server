package com.alkemy.ong.service;

import com.alkemy.ong.model.request.NewsRequestDTO;
import com.alkemy.ong.model.response.news.NewsResponseDTO;


public interface NewsService {
    
    NewsResponseDTO findById(Long id);

    void updateNews(NewsResponseDTO newsResponseDTO, Long id);

    NewsResponseDTO saveNews(NewsRequestDTO request);


}
