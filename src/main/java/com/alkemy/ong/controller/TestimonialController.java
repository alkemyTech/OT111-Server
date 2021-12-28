package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.TestimonialEntity;
import com.alkemy.ong.service.TestimonialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.alkemy.ong.model.request.TestimonialRequestDTO;
import com.alkemy.ong.model.response.TestimonialResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @PostMapping("/")
    public String saveTestiymonial(@Valid @RequestBody TestimonialEntity testimonialEntity, BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println("Error");
            return "";
        }
        testimonialService.saveTestimonial(testimonialEntity);
        log.error("Testimonial saved");
        return " ";
    }

    @PutMapping("/{id}")

    @Operation(summary = "Actualizar testimonio por ID",
            description = "Se actualiza el testimonio por el ID pasado como parámetro por url, " +
                    "y si el testimonio a actualzizar no existe se lanza un error con código de estado 404")
    public ResponseEntity<TestimonialResponseDTO> updateTestimonial(@Valid @RequestBody TestimonialRequestDTO testimonialRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(testimonialService.updateTestimonial(testimonialRequestDTO, id));
    }
}
