package com.alkemy.ong.service;

import com.alkemy.ong.model.response.news.NewsDTO;
import com.alkemy.ong.model.response.pagination.CustomPage;
import org.springframework.data.domain.Pageable;


public interface NewsService {
    NewsDTO findById(Long id);

    CustomPage<NewsDTO> getNewsPageable(Pageable pageable);
    
    void updateNews(NewsDTO newsDTO, Long id);
    

}
