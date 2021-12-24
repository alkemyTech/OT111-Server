package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.ContactEntity;
import com.alkemy.ong.model.mapper.ContactMapper;
import com.alkemy.ong.model.request.ContactRequestDTO;
import com.alkemy.ong.model.response.ContactResponseDTO;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import com.alkemy.ong.service.sendgrid.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final EmailService emailService;

    @Override
    public ContactResponseDTO saveContact(ContactRequestDTO request) {
        ContactEntity newContact = contactMapper.toEntity(request);
        emailService.sendContactConfirmation(request.getEmail());
        ContactEntity savedContact = contactRepository.save(newContact);
        return contactMapper.toDTO(savedContact);
    }

    @Override
    public List<ContactResponseDTO> getContacts() {
        return contactRepository.findAll().stream()
                .map(contactMapper::buildToList)
                .collect(Collectors.toList());
    }
}
