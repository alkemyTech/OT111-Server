package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.OrganizationEntity;
import com.alkemy.ong.model.entity.SlideEntity;
import com.alkemy.ong.model.mapper.SlideMapper;
import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor //TODO: Se esta usando dos formas de injeccion de dependencia porfa normalizar auna
public class SlideServiceImpl implements SlideService {
    private final SlideMapper slideMapper;
    private final SlideRepository slideRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public SlideResponseDTO saveSlide(SlideRequestDTO request) {
        Base64 imagen64 = request.getImagenCodificada();
        Long organizationID = request.getOrganizationId();

        OrganizationEntity orgEnt = organizationRepository.getById(organizationID);


        SlideEntity newSlide = slideMapper.slideDTO2Entity(request,orgEnt,"hola");
        SlideEntity savedSlide = slideRepository.save(newSlide);
        return slideMapper.slideEntity2DTO(savedSlide);


    }
}
