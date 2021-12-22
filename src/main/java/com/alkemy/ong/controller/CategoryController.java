package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.model.response.pagination.CustomPage;
import com.alkemy.ong.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public CategoryResponseDTO getById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @GetMapping("by-combo")
    @Operation(summary = "Lista todas las categorías para mostrar en un combo")
    public List<String> getAll() {
        return categoryService.getCategories();
    }

    @GetMapping
    @Operation(summary = "Lista todas las categorías",
            description = "Obtiene todas las categorías, con un tamaño por defecto de pagina de 10 elementos, " +
                    "ademas retorna el numero de pagina, cantidad de elementos en la pagina, cantidad de elementos total y " +
                    "cantidad total de paginas")
    public CustomPage<CategoryResponseDTO> getAllPage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                      @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return categoryService.getCategoriesPageable(pageRequest);
    }

    @PostMapping
    @Operation(summary = "Crear nueva categoría")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO create(@Valid @RequestBody CategoryRequestDTO request) {
        return categoryService.saveCategory(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoría",
            description = "Actualiza la categoría existente dado el ID pasado como parámetro por url, " +
                    "y si la categoría a actualizar no existe se lanza un error con código de estado 404")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO request) {
        categoryService.updateCategory(request, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoría",
            description = "Elimina la categoría existente dado el ID pasado como parámetro por url, " +
                    "y si la categoría a eliminar no existe se lanza un error con código de estado 404")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

}


