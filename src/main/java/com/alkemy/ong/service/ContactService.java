package com.alkemy.ong.service;

import com.alkemy.ong.model.request.ContactRequestDTO;
import com.alkemy.ong.model.response.ContactResponseDTO;

import java.util.List;

public interface ContactService {

    ContactResponseDTO saveContact(ContactRequestDTO request);

    List<ContactResponseDTO> getContacts();
}
