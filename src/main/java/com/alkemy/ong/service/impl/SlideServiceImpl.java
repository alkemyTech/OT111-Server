package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.SlideEntity;
import com.alkemy.ong.model.mapper.SlideMapper;
import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {
    private final SlideMapper slideMapper;
    private final SlideRepository slideRepository;


    @Override
    public SlideResponseDTO saveSlide(SlideRequestDTO request) {
        SlideEntity newSlide = slideMapper.slideDTO2Entity(request);
        SlideEntity savedSlide = slideRepository.save(newSlide);
        return slideMapper.slideEntity2DTO(savedSlide);
    }
}
