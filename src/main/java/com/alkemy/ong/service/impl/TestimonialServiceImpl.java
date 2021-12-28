package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.TestimonialEntity;
import com.alkemy.ong.model.mapper.TestimonialMapper;
import com.alkemy.ong.model.request.TestimonialRequestDTO;
import com.alkemy.ong.model.response.TestimonialResponseDTO;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;
    private final TestimonialMapper testimonialMapper;


    @Override
    public TestimonialResponseDTO updateTestimonial(TestimonialRequestDTO TestimonialDTO, Long id) {
        TestimonialEntity testimonial = testimonialRepository.findById(id).orElseThrow();
        testimonial.setName(TestimonialDTO.getName());
        testimonial.setContent(TestimonialDTO.getContent());
        testimonial.setImage(TestimonialDTO.getImage());
        testimonialRepository.save(testimonial);
        return testimonialMapper.entity2DTO(testimonial);
    }

    @Override
    public void deleteById(Long id) {
        testimonialRepository.deleteById(id);
    }
}