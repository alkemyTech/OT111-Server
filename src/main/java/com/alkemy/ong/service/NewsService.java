package com.alkemy.ong.service;

import com.alkemy.ong.model.response.News.NewsDTO;


import java.util.Optional;



public interface NewsService {
    NewsDTO findById(Long id);
}
