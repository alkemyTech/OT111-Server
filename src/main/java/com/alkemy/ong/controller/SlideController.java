package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;
import com.alkemy.ong.service.SlideService;
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

@RestController
@RequestMapping("/slides")
@RequiredArgsConstructor
@Tag(name = "slides")
public class SlideController {
    private final SlideService slideService;

    @PostMapping
    @Operation(summary = "crear nuevo slide")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "slide creado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SlideResponseDTO.class))})
    })
    public ResponseEntity<SlideResponseDTO> createNewSlide(@RequestBody SlideRequestDTO request) {
        SlideResponseDTO response = slideService.saveSlide(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar un slide",
    description = "Elimina el slide existente dado el ID pasado como parámetro por url, " +
            "y si el slide a eliminar no existe se lanza un error con código de estado 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "slide eliminado",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = SlideResponseDTO.class))}),
            @ApiResponse(responseCode = "404",description = "not found", content = @Content)
    })
    public ResponseEntity<Void> deleteSlideById(@PathVariable Long id){
        slideService.deleteSlide(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
