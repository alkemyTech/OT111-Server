package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.TestimonialRequestDTO;
import com.alkemy.ong.model.response.TestimonialResponseDTO;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("testimonials")
@Tag(name = "Testimonios")
public class TestimonialController {

    @Autowired
    private TestimonialService testimonialService;
    private TestimonialRepository testimonialRepository;

    @PutMapping("/{id}")

    @Operation(summary = "Actualizar testimonio por ID",
            description = "Se actualiza el testimonio por el ID pasado como parámetro por url, " +
                    "y si el testimonio a actualzizar no existe se lanza un error con código de estado 404")
    public ResponseEntity<TestimonialResponseDTO> updateTestimonial(@Valid @RequestBody TestimonialRequestDTO testimonialRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(testimonialService.updateTestimonial(testimonialRequestDTO, id));
    }
}
