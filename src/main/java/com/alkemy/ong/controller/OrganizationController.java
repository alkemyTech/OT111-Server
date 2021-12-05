package com.alkemy.ong.controller;

import com.alkemy.ong.model.dto.OrganizationDTO;
import com.alkemy.ong.model.entity.OrganizationEntity;
import com.alkemy.ong.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping(value = "/organization/{id}/public ")
    public ResponseEntity test(@RequestBody Long id) {
        return ResponseEntity.ok(OrganizationDTO.smallOrganization(organizationService.readOrganization(id)));
    }

}