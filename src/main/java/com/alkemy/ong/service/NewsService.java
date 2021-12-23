package com.alkemy.ong.service;


import com.alkemy.ong.model.request.NewsRequestDTO;
import com.alkemy.ong.model.response.news.NewsResponseDTO;
import com.alkemy.ong.model.response.pagination.CustomPage;
import org.springframework.data.domain.Pageable;

public interface NewsService {


    NewsResponseDTO findNewsById(Long id);

    NewsResponseDTO updateNews(NewsRequestDTO newsRequestDTO, Long id);

    NewsResponseDTO saveNews(NewsRequestDTO request);

    CustomPage<NewsResponseDTO> getNewsPageable(Pageable pageable);

    void deleteNews(Long id);

}
