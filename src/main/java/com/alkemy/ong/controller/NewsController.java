package com.alkemy.ong.controller;

import com.alkemy.ong.exception.ApiException;
import com.alkemy.ong.model.request.NewsRequestDTO;
import com.alkemy.ong.model.response.news.NewsResponseDTO;
import com.alkemy.ong.model.response.pagination.CustomPage;
import com.alkemy.ong.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Novedades")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @Operation(summary = "Obtener novedad por ID", description = "Obtiene los detalles de" +
            " la novedad por el ID pasado como parámetro por url, " +
            "y si no existe se lanza un error con código de estado 404")
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = NewsResponseDTO.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Novedad no encontrada",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ApiException.class))
                            }
                    ),
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDTO> findById(@PathVariable Long id) {
        NewsResponseDTO newsResponseDTO = newsService.findNewsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(newsResponseDTO);
    }

    @Operation(summary = "Agrega una novedad en el sistema",
            description = "Crea una novedad ingresando como datos: nombre*, contenido*, imagen y el ID* de categoría asociada. " +
                    "Retornara los datos cargados de novedad y de su categoría asociada " +
                    "En caso que se omita algún dato requerido se devolverá mensaje de error, asi mismo " +
                    "se comprobará si la categoría asociada existen en el sistema, si no se lanza un error con código de estado 404.")
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = NewsResponseDTO.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Constraint Violation: Datos no validos",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ApiException.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Categoría no encontrada",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ApiException.class))
                            }
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<NewsResponseDTO> createNews(@RequestBody NewsRequestDTO newsRequestDTO) {
        NewsResponseDTO response = newsService.saveNews(newsRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Operation(summary = "Lista las novedades por página",
            description = "Obtiene todas las novedades, con un tamaño por defecto de pagina de 10 elementos, " +
                    "ademas retorna el numero de pagina, cantidad de elementos en la pagina, cantidad de elementos total y " +
                    "cantidad total de paginas. Opcionalmente puede ingresar como parámetros el numero de pagina o cantidad de elementos por pagina.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomPage.class))})})
    @GetMapping
    public ResponseEntity<CustomPage<NewsResponseDTO>> getNewsPage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        var response = newsService.getNewsPageable(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Actualizar novedad por ID",
            description = "Actualiza una novedad por ID, pasado como parámetro por url, " +
                    "ingresando como datos: nombre*, contenido*, imagen y el ID* de categoría asociada. " +
                    "Retornara los datos cargados de novedad y ademas de la categoría asociada " +
                    "En caso que se omita algún dato requerido se devolverá mensaje de error, asi mismo " +
                    "se comprobará si la novedad y su categoría asociada existen en el sistema si no se lanza un error con código de estado 404 ")

    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = NewsResponseDTO.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Constraint Violation: Datos no validos",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ApiException.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Novedad o Categoría no encontrada",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ApiException.class))
                            }
                    ),
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<NewsResponseDTO> updateNews(@RequestBody NewsRequestDTO NewsRequestDTO, @PathVariable Long id) {
        NewsResponseDTO response = newsService.updateNews(NewsRequestDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Operation(summary = "Eliminar una Novedad",
            description = "Elimina la novedad existente dado el ID pasado como parámetro por url, " +
                    "y si la categoría a eliminar no existe se lanza un error con código de estado 404")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Novedad Eliminada",
                    content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "Novedad o Categoría no encontrada",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiException.class))
                    }
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewsById(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}

