package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.NewsRequestDTO;
import com.alkemy.ong.model.response.news.NewsResponseDTO;
import com.alkemy.ong.model.response.pagination.CustomPage;
import com.alkemy.ong.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDTO> findById(@PathVariable Long id) {
        NewsResponseDTO newsResponseDTO = newsService.findNewsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(newsResponseDTO);
    }

    @PostMapping
    public ResponseEntity<NewsResponseDTO> createNews(@RequestBody NewsRequestDTO newsRequestDTO) {
        NewsResponseDTO response = newsService.saveNews(newsRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Lista todas las novedades")
    public ResponseEntity<CustomPage<NewsResponseDTO>> getNewsPage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        var response = newsService.getNewsPageable(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar novedad por ID",
            description = "Se actualiza la novedad por el ID pasado como parámetro por url, " +
                    "y si la novedad a actualzizar no existe se lanza un error con código de estado 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Novedad actualizada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewsResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<NewsResponseDTO> updateNews(@RequestBody NewsRequestDTO NewsRequestDTO, @PathVariable Long id) {
        NewsResponseDTO response = newsService.updateNews(NewsRequestDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewsById(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
