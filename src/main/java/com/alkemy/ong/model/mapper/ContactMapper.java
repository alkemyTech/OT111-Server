package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.ContactEntity;
import com.alkemy.ong.model.request.ContactRequestDTO;
import com.alkemy.ong.model.response.ContactResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public ContactEntity toEntity(ContactRequestDTO dto) {
        ContactEntity entity = new ContactEntity();
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setMessage(dto.getMessage());

        return entity;
    }

    public ContactResponseDTO toDTO(ContactEntity entity) {
        ContactResponseDTO dto = new ContactResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setMessage(entity.getMessage());

        return dto;
    }

    public ContactResponseDTO buildToList(ContactEntity entity) {
        return ContactResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .message(entity.getMessage())
                .build();
    }
}
