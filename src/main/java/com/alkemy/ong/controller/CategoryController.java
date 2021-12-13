package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Categorias")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoría por ID",
            description = "Obtiene la categoría por el ID pasado como parámetro por url, " +
                    "y si no existe se lanza un error con código de estado 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<CategoryResponseDTO> getCategoryDetails(@PathVariable Long id) {
        CategoryResponseDTO categoryDetails = categoryService.findCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDetails);
    }

    @GetMapping
    @Operation(summary = "Lista todas las categorías")
    public ResponseEntity<List<CategoryResponseDTO>> getCategories() {
        var response = categoryService.getCategories();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @Operation(summary = "Crear nueva categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseDTO.class))})
    })
    public ResponseEntity<CategoryResponseDTO> createNewCategory(@RequestBody CategoryRequestDTO request) {
        CategoryResponseDTO response = categoryService.saveCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoría",
            description = "Actualiza la categoría existente dado el ID pasado como parámetro por url, " +
                    "y si la categoría a actualizar no existe se lanza un error con código de estado 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria actualizada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryRequestDTO request, @PathVariable Long id) {
        categoryService.updateCategory(request, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoría",
            description = "Elimina la categoría existente dado el ID pasado como parámetro por url, " +
                    "y si la categoría a eliminar no existe se lanza un error con código de estado 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria eliminada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}


