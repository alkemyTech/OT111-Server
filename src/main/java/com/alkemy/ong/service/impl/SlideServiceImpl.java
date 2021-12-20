package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.OrganizationEntity;
import com.alkemy.ong.model.entity.SlideEntity;
import com.alkemy.ong.model.mapper.SlideMapper;
import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.AWSResponseDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.AWSService;
import com.alkemy.ong.service.SlideService;
import com.alkemy.ong.utils.BASE64DecodedMultipartFile;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor //TODO: Se esta usando dos formas de injeccion de dependencia porfa normalizar auna
public class SlideServiceImpl implements SlideService {
    private final SlideMapper slideMapper;
    private final SlideRepository slideRepository;
    private final OrganizationRepository organizationRepository;
    private final AWSService awsService;
    private final BASE64DecodedMultipartFile base64DecodedMultipartFile;

    @Override
    public SlideResponseDTO saveSlide(SlideRequestDTO request) {
        Long organizationID = request.getOrganizationId();
        OrganizationEntity orgEnt = organizationRepository.getById(organizationID);

        BASE64DecodedMultipartFile cosa = new BASE64DecodedMultipartFile(Base64.decodeBase64(request.getImagenCodificada()));
        System.out.println("===============");
        System.out.println(request.getImagenCodificada());
        System.out.println(cosa.getOriginalFilename());
        System.out.println("===============");

        AWSResponseDTO imagenUrl = awsService.uploadFile(cosa);
        System.out.println("=========");
        System.out.println(imagenUrl.getImageUrl());
        System.out.println("=========");
        SlideEntity newSlide = slideMapper.slideDTO2Entity(request, orgEnt, imagenUrl.getImageUrl());

        SlideEntity savedSlide = slideRepository.save(newSlide);
        return slideMapper.slideEntity2DTO(savedSlide);
    }

    @Override
    public SlideResponseDTO findSlideById(Long id) {
        SlideEntity foundSlide = slideRepository.findById(id).orElseThrow();
        return slideMapper.slideEntity2DTO(foundSlide);
    }
}
