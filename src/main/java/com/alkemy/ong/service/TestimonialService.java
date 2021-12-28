package com.alkemy.ong.service;

import com.alkemy.ong.model.request.TestimonialRequestDTO;
import com.alkemy.ong.model.response.TestimonialResponseDTO;

public interface TestimonialService {

    void deleteById(Long id);

    TestimonialResponseDTO saveTestimonial(TestimonialRequestDTO testimonialRequestDTO);

    TestimonialResponseDTO updateTestimonial(TestimonialRequestDTO TestimonialDTO, Long id);

}
