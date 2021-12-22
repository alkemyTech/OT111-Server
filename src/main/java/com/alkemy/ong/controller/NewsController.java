package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.NewsRequestDTO;
import com.alkemy.ong.model.response.news.NewsResponseDTO;
import com.alkemy.ong.model.response.pagination.CustomPage;
import com.alkemy.ong.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("news")
@Tag(name = "Novedades")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @Operation(summary = "Obtener novedad por ID", description = "Obtiene los detalles de" +
            " la novedad por el ID pasado como parámetro por url, " +
            "y si no existe se lanza un error con código de estado 404")
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
    @PostMapping
    public ResponseEntity<NewsResponseDTO> createNews(@Valid @RequestBody NewsRequestDTO newsRequestDTO) {
        NewsResponseDTO response = newsService.saveNews(newsRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Operation(summary = "Lista las novedades por página",
            description = "Obtiene todas las novedades, con un tamaño por defecto de pagina de 10 elementos, " +
                    "ademas retorna el numero de pagina, cantidad de elementos en la pagina, cantidad de elementos total y " +
                    "cantidad total de paginas. Opcionalmente puede ingresar como parámetros el numero de pagina o cantidad de elementos por pagina.")
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
    @PutMapping("/{id}")
    public ResponseEntity<NewsResponseDTO> updateNews(@Valid @RequestBody NewsRequestDTO newsRequestDTO, @PathVariable Long id) {
        NewsResponseDTO response = newsService.updateNews(newsRequestDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Eliminar una Novedad",
            description = "Elimina la novedad existente dado el ID pasado como parámetro por url, " +
                    "y si la categoría a eliminar no existe se lanza un error con código de estado 404")
    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteNewsById(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

