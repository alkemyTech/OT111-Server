package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.ContactRequestDTO;
import com.alkemy.ong.model.response.ContactResponseDTO;
import com.alkemy.ong.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
@Tag(name = "Contactos")
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    @Operation(summary = "Devuelve una List de Contacts", description = "Devuelve los atributos: Id, Name, Phone, Email, Message.")
    @ResponseStatus(HttpStatus.OK)
    public List<ContactResponseDTO> getContacts() {
        return contactService.getContacts();
    }

    @PostMapping
    @Operation(summary = "Creará un nuevo contacto", description = "Campos obligatorios: Name, Email, Message.")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponseDTO createNewContact(@Valid @RequestBody ContactRequestDTO request) {
        return contactService.saveContact(request);
    }

}
