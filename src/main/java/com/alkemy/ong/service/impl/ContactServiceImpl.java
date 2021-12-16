package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.ContactEntity;
import com.alkemy.ong.model.mapper.ContactMapper;
import com.alkemy.ong.model.request.ContactRequestDTO;
import com.alkemy.ong.model.response.ContactResponseDTO;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public ContactResponseDTO saveContact(ContactRequestDTO request) {
        ContactEntity newContact = contactMapper.contactDTO2Entity(request);
        ContactEntity savedContact = contactRepository.save(newContact);
        return contactMapper.contactEntity2DTO(savedContact);
    }

    @Override
    public List<ContactResponseDTO> getContacts() {
        return contactRepository.findAll().stream().map(contactMapper::buildToList).collect(Collectors.toList());
    }
}
