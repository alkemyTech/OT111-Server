package com.alkemy.ong.controller;

import com.alkemy.ong.model.dto.TestimonialDTO;
import com.alkemy.ong.model.request.TestimonialRequestDTO;
import com.alkemy.ong.model.response.TestimonialResponseDTO;
import com.alkemy.ong.repository.TestimonialRepositoty;
import com.alkemy.ong.service.TestimonialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("testimonials")
public class TestimonialController {

    @Autowired
    private TestimonialService testimonialService;
    private TestimonialRepositoty testimonialRepositoty;

    @PutMapping("/{id}")

    @Operation(summary = "Actualizar testimonio por ID",
            description = "Se actualiza el testimonio por el ID pasado como parámetro por url, " +
                    "y si el testimonio a actualzizar no existe se lanza un error con código de estado 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Testimonio actualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TestimonialDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<TestimonialResponseDTO> updateTestimonial(@RequestBody TestimonialRequestDTO TestimonialRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(testimonialService.updateTestimonial(TestimonialRequestDTO, id));
    }
}
