package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.news.NewsDTO;
import com.alkemy.ong.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> findById(@PathVariable Long id) {
        NewsDTO newsDTO = newsService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(newsDTO);
    }

    @GetMapping
    @Operation(summary = "Lista todas las novedades")
    public ResponseEntity<List<NewsDTO>> getNews() {
        var response = newsService.getNews();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
