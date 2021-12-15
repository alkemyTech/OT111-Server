package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.news.NewsDTO;
import com.alkemy.ong.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
@Tag(name = "Novedades")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener novedad por ID",
            security = @SecurityRequirement(name = "bearerAuth"),
            description = "Obtiene los detalles de la novedad por el ID pasado como parámetro por url, " +
                    "y si no existe se lanza un error con código de estado 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewsDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<NewsDTO> findById(@PathVariable Long id) {
        NewsDTO newsDTO = newsService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(newsDTO);
    }


    //TODO add documentation to others endpoints
}
