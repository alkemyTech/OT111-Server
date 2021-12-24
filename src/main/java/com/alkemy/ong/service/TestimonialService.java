package com.alkemy.ong.service;

import com.alkemy.ong.model.dto.TestimonialDTO;
import com.alkemy.ong.model.response.TestimonialResponseDTO;

public interface TestimonialService {
    TestimonialResponseDTO updateTestimonial(TestimonialDTO TestimonialDTO, Long id);
}
