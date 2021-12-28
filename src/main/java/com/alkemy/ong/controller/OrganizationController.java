package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.OrganizationRequest;
import com.alkemy.ong.model.response.OrganizationFullResponse;
import com.alkemy.ong.model.response.OrganizationPublicResponse;
import com.alkemy.ong.service.OrganizationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RestController
@RequestMapping("/organization/public")
@RequiredArgsConstructor
@Tag(name = "Organización")
@Validated
public class OrganizationController {

    private final OrganizationService organizationService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtiene los datos públicos de la organización",
            description = "Muestra los datos públicos al usuario: nombre, imagen, teléfono, dirección y " +
                    "redes sociales de la organización.")
    @GetMapping
    public OrganizationPublicResponse getOrganizationPublic() {
        return organizationService.getOrganization();
    }

    @Operation(summary = "Crea una nueva organización",
            description = "Crea una organización como usuario administrador, validando los campos requeridos")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrganizationFullResponse createOrganization(@Valid @RequestBody OrganizationRequest request) {
        return organizationService.saveOrganization(request);
    }

    @Operation(summary = "Actualiza los datos de la organización",
            description = "Editar todos los datos de la organización como usuario administrador, validando los campos requeridos")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public OrganizationFullResponse updateOrganization(@Valid @RequestBody OrganizationRequest organizationRequest) {
        return organizationService.updateOrganization(organizationRequest);
    }

    @Operation(summary = "Elimina una organización",
            description = "Elimina la organización existente pasando el ID como parámetro por url," +
                    "si la organización a eliminar no existe lanza error")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteOrganizationById(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
    }
}