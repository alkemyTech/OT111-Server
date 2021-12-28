package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.TestimonialEntity;
import com.alkemy.ong.service.TestimonialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/testimonials")
@Slf4j
public class TestimonialController {

    @Autowired
    private TestimonialService testimonialService;

    @PostMapping("/")
    public String saveTestimonial(@Valid @RequestBody TestimonialEntity testimonialEntity, BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println("Error");
            return "";
        }
        testimonialService.saveTestimonial(testimonialEntity);
        log.error("Testimonial saved");
        return " ";
    }

}
