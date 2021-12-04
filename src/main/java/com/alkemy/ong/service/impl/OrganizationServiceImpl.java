package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.OrganizationEntity;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;

public class OrganizationServiceImpl implements OrganizationService {
    @Override
    public OrganizationEntity readOrganization(Long id) {
        return OrganizationRepository.getById(id);
    }
}
