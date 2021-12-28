package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.SlideEntity;
import com.alkemy.ong.model.mapper.SlideMapper;
import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.AWSResponseDTO;
import com.alkemy.ong.model.response.SlideListadoResponseDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.AWSService;
import com.alkemy.ong.service.BASE64DecodedMultipartFile;
import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        SlideEntity foundSlide = slideRepository.findById(id).orElseThrow();
        var orgEnt = organizationRepository.findById(request.getOrganizationId()).orElseThrow();

        BASE64DecodedMultipartFile multiPart = new BASE64DecodedMultipartFile(Base64.decodeBase64(request.getImagenCodificada()));
        AWSResponseDTO imagenUrl = awsService.uploadFile(multiPart);

        foundSlide.setText(request.getText());
        foundSlide.setImageUrl(imagenUrl.getImageUrl());
        foundSlide.setOrder(request.getOrder());
        foundSlide.setOrganization(orgEnt);
        slideRepository.save(foundSlide);
    }

    @Override
    public void deleteSlide(Long id) {
        var foundSlide = slideRepository.findById(id).orElseThrow();
        slideRepository.delete(foundSlide);
    }

    @Override
    public List<SlideListadoResponseDTO> getSlides() {
        return slideRepository.findAll().stream()
                .map(slideMapper::toDtoListado)
                .collect(Collectors.toList());
    }

    @Override
    public SlideResponseDTO saveSlide(SlideRequestDTO request) {
        Long organizationID = request.getOrganizationId();
        var orgEnt = organizationRepository.findById(organizationID).orElseThrow();

        BASE64DecodedMultipartFile multiPart = new BASE64DecodedMultipartFile(Base64.decodeBase64(request.getImagenCodificada()));
        AWSResponseDTO imagenUrl = awsService.uploadFile(multiPart);

        SlideEntity newSlide = slideMapper.toEntity(request, orgEnt, imagenUrl.getImageUrl());
        SlideEntity savedSlide = slideRepository.save(newSlide);
        return slideMapper.toDTO(savedSlide);

    }
}
