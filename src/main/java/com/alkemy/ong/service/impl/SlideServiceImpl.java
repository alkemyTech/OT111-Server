package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.SlideEntity;
import com.alkemy.ong.model.mapper.SlideMapper;
import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.AWSService;
import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {
    private final SlideMapper slideMapper;
    private final SlideRepository slideRepository;
    private final OrganizationRepository organizationRepository;
    private final AWSService awsService;

    @Override
    public SlideResponseDTO findSlideById(Long id) {
        SlideEntity foundSlide = slideRepository.findById(id).orElseThrow();
        return slideMapper.toDTO(foundSlide);
    }

    @Override
    public void updateSlide(SlideRequestDTO request, Long id) {
        SlideEntity foundSlide =slideRepository.findById(id).orElseThrow();
        foundSlide.setText(request.getText());
        foundSlide.setImageUrl(String.valueOf(request.getImagenCodificada()));
        foundSlide.setOrder(request.getOrder());
        slideRepository.save(foundSlide);
    }

    @Override
    public void deleteSlide(Long id) {
        var foundSlide = slideRepository.findById(id).orElseThrow();
        slideRepository.delete(foundSlide);
    }
}
