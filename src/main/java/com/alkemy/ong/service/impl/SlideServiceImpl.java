package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.OrganizationEntity;
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

import java.util.Base64;

@Service
@RequiredArgsConstructor //TODO: Se esta usando dos formas de injeccion de dependencia porfa normalizar auna
public class SlideServiceImpl implements SlideService {
    private final SlideMapper slideMapper;
    private final SlideRepository slideRepository;
    private final OrganizationRepository organizationRepository;
    private final AWSService awsService;

    @Override
    public SlideResponseDTO saveSlide(SlideRequestDTO request) {
        Long organizationID = request.getOrganizationId();
        OrganizationEntity orgEnt = organizationRepository.getById(organizationID);
        byte[] misBytes = Base64.getDecoder().decode(request.getImagenCodificada());

        String imagenUrl = awsService.uploadFile();

        SlideEntity newSlide = slideMapper.slideDTO2Entity(request, orgEnt, imagenUrl);
        SlideEntity savedSlide = slideRepository.save(newSlide);
        return slideMapper.slideEntity2DTO(savedSlide);
    }

    @Override
    public SlideResponseDTO findSlideById(Long id) {
        SlideEntity foundSlide = slideRepository.findById(id).orElseThrow();
        return slideMapper.slideEntity2DTO(foundSlide);
    }
}
