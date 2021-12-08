package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.OrganizationEntity;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationEntity readOrganization(Long id) {
        return organizationRepository.findById(id).orElseThrow();
    }

}
