package com.alkemy.ong.service;

import com.alkemy.ong.model.request.TestimonialRequestDTO;
import com.alkemy.ong.model.response.TestimonialResponseDTO;

public interface TestimonialService {

    TestimonialResponseDTO updateTestimonial(TestimonialRequestDTO TestimonialDTO, Long id);

    void deleteById(Long id);
}
