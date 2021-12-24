package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.dto.TestimonialDTO;
import com.alkemy.ong.model.entity.TestimonialEntity;
import com.alkemy.ong.model.mapper.TestimonialMapper;
import com.alkemy.ong.model.response.TestimonialResponseDTO;
import com.alkemy.ong.repository.TestimonialRepositoty;
import com.alkemy.ong.service.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepositoty testimonialRepositoty;
    private final TestimonialMapper testimonialMapper;


    @Override
    public TestimonialResponseDTO updateTestimonial(TestimonialDTO TestimonialDTO, Long id) {
        TestimonialEntity testimonial = testimonialRepositoty.findById(id).orElseThrow();
        testimonial.setName(TestimonialDTO.getName());
        testimonial.setContent(TestimonialDTO.getContent());
        testimonial.setImage(TestimonialDTO.getImage());
        testimonialRepositoty.save(testimonial);
        return testimonialMapper.testimonialEntity2DTO(testimonial);
    }
}