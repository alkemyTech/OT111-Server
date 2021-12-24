package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.SlideResponseDTO;
import com.alkemy.ong.service.SlideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public SlideResponseDTO getSlideDetails(@PathVariable Long id){
        return slideService.findSlideById(id);
    }

}
