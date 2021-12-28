package com.alkemy.ong.controller;

import com.alkemy.ong.service.TestimonialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.alkemy.ong.model.request.TestimonialRequestDTO;
import com.alkemy.ong.model.response.TestimonialResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@Tag(name = "Testimonios")
@RequestMapping("/testimonials")
public class TestimonialController {

    @Autowired
    private TestimonialService testimonialService;

    @PostMapping
    @Operation(summary = "Crear Nuevo Testimonial")
    @ResponseStatus(HttpStatus.CREATED)
    public TestimonialResponseDTO saveTestimonial(@Valid @RequestBody TestimonialRequestDTO testimonialRequestDTO) {
        return testimonialService.saveTestimonial(testimonialRequestDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar testimonio por ID",
            description = "Se actualiza el testimonio por el ID pasado como parámetro por url, " +
                    "y si el testimonio a actualzizar no existe se lanza un error con código de estado 404")
    public ResponseEntity<TestimonialResponseDTO> updateTestimonial(@Valid @RequestBody TestimonialRequestDTO testimonialRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(testimonialService.updateTestimonial(testimonialRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar testimonio por ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTestimonialById(@PathVariable Long id) {
        testimonialService.deleteById(id);
    }

}
