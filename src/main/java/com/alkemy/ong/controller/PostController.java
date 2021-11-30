package com.alkemy.ong.controller;

<<<<<<< Updated upstream
import com.alkemy.ong.model.dto.DTOOrganization;
=======
import com.alkemy.ong.model.dto.OrganizationDTO;
>>>>>>> Stashed changes
import com.alkemy.ong.service.PostManagmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
class TestCont {

    @GetMapping(value = "/organization/public")
    public ResponseEntity test() {
<<<<<<< Updated upstream
        DTOOrganization organization = PostManagmentService.readOrganization();
=======
        OrganizationDTO organization = PostManagmentService.readOrganization();
>>>>>>> Stashed changes
        Map<String, String> response = new HashMap<String,String>();
        response.put("name",organization.getName());
        response.put("image",organization.getImage());
        response.put("phone",Integer.toString(organization.getPhone()));
        response.put("address",organization.getAddress());
        return ResponseEntity.ok(response);
    }

}