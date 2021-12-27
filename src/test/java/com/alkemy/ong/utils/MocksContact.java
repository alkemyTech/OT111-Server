package com.alkemy.ong.utils;

import com.alkemy.ong.model.entity.ContactEntity;
import com.alkemy.ong.model.request.ContactRequestDTO;
import com.alkemy.ong.model.response.ContactResponseDTO;

public class MocksContact {

    public static ContactEntity buildContactEntity() {
        return ContactEntity.builder()
                .name("Mock Contact")
                .phone("Mock Phone")
                .email("Mock Email")
                .message("Mock Message")
                .build();
    }

    public static ContactRequestDTO buildContactRequest() {
        return ContactRequestDTO.builder()
                .name("Mock Contact")
                .phone("Mock Phone")
                .email("Mock Email")
                .message("Mock Message")
                .build();
    }

    public static ContactRequestDTO buildContactRequestInvalid() {
        return ContactRequestDTO.builder()
                .name("")
                .phone("Mock Phone")
                .email("")
                .message("")
                .build();
    }


}
