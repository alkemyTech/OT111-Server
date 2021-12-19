package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.NewsRequestDTO;
import com.alkemy.ong.model.response.news.NewsResponseDTO;
import com.alkemy.ong.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDTO> findById(@PathVariable Long id) {
        NewsResponseDTO newsResponseDTO = newsService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(newsResponseDTO);
    }

    @PostMapping
    public ResponseEntity<NewsResponseDTO> createNews(@RequestBody NewsRequestDTO newsRequestDTO) {
        NewsResponseDTO response = newsService.saveNews(newsRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Obtener novedad por ID",
            description = "Obtiene la novedad por el ID pasado como parámetro por url, " +
                    "y si no existe se lanza un error con código de estado 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Novedad actualizada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewsResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<Void> updateNews(@RequestBody NewsResponseDTO newsResponseDTO, @PathVariable Long id) {
        newsService.updateNews(newsResponseDTO, id);
        return ResponseEntity.ok().build();
    }
}
