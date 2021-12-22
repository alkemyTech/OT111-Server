package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.request.OrganizationRequest;
import com.alkemy.ong.model.response.OrganizationFullResponse;
import com.alkemy.ong.model.response.OrganizationPublicResponse;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationPublicResponse getOrganization() {
        var organization = organizationRepository.findTopByOrderByIdDesc().orElseThrow();
        return OrganizationPublicResponse.toDTO(organization);
    }

    @Override
    @Transactional
    public OrganizationFullResponse updateOrganization(OrganizationRequest organizationRequest) {
        var foundOrganization = organizationRepository.findTopByOrderByIdDesc().orElseThrow();
        return OrganizationFullResponse.toDTO(organizationRepository
                .save(OrganizationFullResponse.refreshData(foundOrganization, organizationRequest)));

    }

    @Override
    @Transactional
    public OrganizationFullResponse saveOrganization(OrganizationRequest request) {
        return OrganizationFullResponse.toDTO(organizationRepository.save(OrganizationFullResponse.toEntity(request)));
    }

    @Override
    @Transactional
    public void deleteOrganization(Long id) {
        var foundOrganization = organizationRepository.findById(id).orElseThrow();
        organizationRepository.delete(foundOrganization);
    }

}
