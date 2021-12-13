package com.alkemy.ong.service;

import com.alkemy.ong.model.request.NewsRequestDTO;
import com.alkemy.ong.model.response.news.NewsResponseDTO;

public interface NewsService {

    NewsResponseDTO saveNews(NewsRequestDTO request);
}
