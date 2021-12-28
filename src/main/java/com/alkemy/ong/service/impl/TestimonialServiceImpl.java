package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.TestimonialEntity;
import com.alkemy.ong.model.request.TestimonialRequestDTO;
import com.alkemy.ong.model.response.TestimonialResponseDTO;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Override
    public void saveTestimonial(TestimonialEntity testimonialEntity) {
        testimonialRepository.save(testimonialEntity);
    }

    @Override
    public TestimonialResponseDTO updateTestimonial(TestimonialRequestDTO TestimonialDTO, Long id) {
        TestimonialEntity testimonial = testimonialRepository.findById(id).orElseThrow();
        testimonial.setName(TestimonialDTO.getName());
        testimonial.setContent(TestimonialDTO.getContent());
        testimonial.setImage(TestimonialDTO.getImage());
        testimonialRepository.save(testimonial);
        return testimonialMapper.entity2DTO(testimonial);
    }

}
