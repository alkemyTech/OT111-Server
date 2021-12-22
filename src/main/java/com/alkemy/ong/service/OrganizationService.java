package com.alkemy.ong.service;

import com.alkemy.ong.model.request.OrganizationRequest;
import com.alkemy.ong.model.response.OrganizationFullResponse;
import com.alkemy.ong.model.response.OrganizationPublicResponse;

public interface OrganizationService {

    OrganizationPublicResponse readOrganization();

    OrganizationFullResponse updateOrganization(OrganizationRequest organizationRequest);
}
