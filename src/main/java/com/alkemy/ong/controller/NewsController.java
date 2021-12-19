package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.news.NewsDTO;
import com.alkemy.ong.model.response.pagination.CustomPage;
import com.alkemy.ong.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CustomPage<NewsDTO>> getNewsPage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
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
                            schema = @Schema(implementation = NewsDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<Void> updateNews(@RequestBody NewsDTO newsDTO, @PathVariable Long id) {
        newsService.updateNews(newsDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}") //TODO: Dberia ser un deleted
    public ResponseEntity<Void> deleteNewsById(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
