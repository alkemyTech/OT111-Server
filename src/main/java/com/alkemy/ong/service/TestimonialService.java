package com.alkemy.ong.service;

import com.alkemy.ong.model.entity.TestimonialEntity;
import com.alkemy.ong.model.request.TestimonialRequestDTO;
import com.alkemy.ong.model.response.TestimonialResponseDTO;

public interface TestimonialService {

    TestimonialResponseDTO saveTestimonial(TestimonialRequestDTO testimonialRequestDTO);

    TestimonialResponseDTO updateTestimonial(TestimonialRequestDTO TestimonialDTO, Long id);

}
