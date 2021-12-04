package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.OrganizationEntity;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;

import javax.transaction.Transactional;

public class OrganizationServiceImpl implements OrganizationService {
    @Override
    @Transactional
    public OrganizationEntity readOrganization(Long id) {
        return OrganizationRepository.getById(id);
    }
}
