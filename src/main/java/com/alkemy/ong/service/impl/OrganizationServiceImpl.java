package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.SlideEntity;
import com.alkemy.ong.model.mapper.AbstractMapper;
import com.alkemy.ong.model.mapper.SlideMapper;
import com.alkemy.ong.model.request.OrganizationRequest;
import com.alkemy.ong.model.response.OrganizationFullResponse;
import com.alkemy.ong.model.response.OrganizationPublicResponse;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final SlideRepository slideRepository;
    private final SlideMapper slideMapper;

    @Override
    public OrganizationPublicResponse getOrganization() {
        var organization = organizationRepository.findTopByOrderByIdDesc().orElseThrow();
        var slidesEnt = slideRepository.findByOrganizationId(organization.getId()).stream()
                .map(ent -> slideMapper.toDTONoOrg(ent))
                .collect(Collectors.toList());
        return OrganizationPublicResponse.toDTO(organization, slidesEnt);
    }

    @Override
    @Transactional
    public OrganizationFullResponse updateOrganization(OrganizationRequest organizationRequest) {
        var foundOrganization = organizationRepository.findTopByOrderByIdDesc().orElseThrow();
        return OrganizationFullResponse.toDTO(organizationRepository
                .save(OrganizationRequest.refreshData(foundOrganization, organizationRequest)));

    }

    @Override
    @Transactional
    public OrganizationFullResponse saveOrganization(OrganizationRequest request) {
        return OrganizationFullResponse.toDTO(organizationRepository.save(OrganizationRequest.toEntity(request)));
    }

    @Override
    @Transactional
    public void deleteOrganization(Long id) {
        var foundOrganization = organizationRepository.findById(id).orElseThrow();
        organizationRepository.delete(foundOrganization);
    }

}
