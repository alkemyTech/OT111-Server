package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.OrganizationRequest;
import com.alkemy.ong.model.response.OrganizationFullResponse;
import com.alkemy.ong.model.response.OrganizationPublicResponse;
import com.alkemy.ong.service.OrganizationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
@Tag(name = "Organización")
@Validated
class OrganizationController {

    private final OrganizationService organizationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/public")
    public OrganizationPublicResponse getOrganization() {
        return organizationService.readOrganization();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/public")
    public OrganizationFullResponse updateOrganization(@Valid @RequestBody OrganizationRequest organizationRequest) {
        return organizationService.updateOrganization(organizationRequest);
    }
}