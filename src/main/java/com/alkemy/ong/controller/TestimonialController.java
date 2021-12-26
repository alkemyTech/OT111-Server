package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.TestimonialEntity;
import com.alkemy.ong.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

    @Autowired
    private TestimonialService testimonialService;

    @PostMapping("/")
    public String saveTestimonial(@Valid @ModelAttribute TestimonialEntity testimonialEntity, BindingResult result, Model model){

        if (result.hasErrors()){

            System.out.println("Error");

            return "";
        }
        testimonialService.saveTestimonial(testimonialEntity);
        System.out.println("Testimonial saved");

        return " ";
    }

}
