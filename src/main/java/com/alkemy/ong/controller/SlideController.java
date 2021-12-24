package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;
import com.alkemy.ong.service.SlideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/slides")
@RequiredArgsConstructor
@Tag(name = "slides")
public class SlideController {

    private final SlideService slideService;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener slide por Id",
            description = "Obtiene el slide creado por el id pasado como parametro, " +
                    "y si no existe lanza un error con codigo de estado 404")
    public SlideResponseDTO getSlideDetails(@PathVariable Long id) {
        return slideService.findSlideById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar un slide",
            description = "Actualiza el slide existente dado el ID pasado como parámetro por url, " +
                    "y si el slide a actualizar no existe se lanza un error con código de estado 404")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSlide(@Valid @RequestBody SlideRequestDTO request, @PathVariable Long id) {
        slideService.updateSlide(request, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar un slide",
            description = "Elimina el slide existente dado el ID pasado como parámetro por url, " +
                    "y si el slide a eliminar no existe se lanza un error con código de estado 404")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSlideById(@PathVariable Long id) {
        slideService.deleteSlide(id);
    }

    @GetMapping("by-combo")
    @Operation(summary = "lista todos los slides")
    public List<Integer> getAll() {
        return slideService.getSlides();
    }


}
