package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.request.NewsRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.model.response.news.NewsResponseDTO;
import com.alkemy.ong.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
@RequiredArgsConstructor
@Tag(name = "Noticias")
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    @Operation(summary = "Crear nueva noticia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Noticia creada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewsResponseDTO.class))})
    })
    public ResponseEntity<NewsResponseDTO> createNewNews(@RequestBody NewsRequestDTO request) {
        NewsResponseDTO response = newsService.saveNews(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
