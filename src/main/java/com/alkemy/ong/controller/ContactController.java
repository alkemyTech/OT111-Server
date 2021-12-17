package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.ContactRequestDTO;
import com.alkemy.ong.model.response.ContactResponseDTO;
import com.alkemy.ong.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
@Tag(name = "Contactos")
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    @Operation(summary = "Lista todos los contactos")
    public ResponseEntity<List<ContactResponseDTO>> getContacts() {
        var response = contactService.getContacts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contacto creado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ContactResponseDTO.class))})
    })
    public ResponseEntity<ContactResponseDTO> createNewContact(@RequestBody ContactRequestDTO request) {
        ContactResponseDTO response = contactService.saveContact(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
