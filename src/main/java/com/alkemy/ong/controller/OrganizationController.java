package com.alkemy.ong.controller;

import com.alkemy.ong.model.dto.OrganizationDTO;
import com.alkemy.ong.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
class OrganizationController {

    @Autowired
    private OrganizationService organizationService;


    @GetMapping("/{id}/public")
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable Long id) {
        return ResponseEntity.ok(OrganizationDTO.buildPublicData(organizationService.readOrganization(id)));
    }

}