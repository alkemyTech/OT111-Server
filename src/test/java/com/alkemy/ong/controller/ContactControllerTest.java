package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.ContactEntity;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.utils.MocksContact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    private static final String PATH = "/contacts";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactRepository contactRepository;

    private ContactEntity savedContact;

    @BeforeEach
    void setUp() {
        savedContact = contactRepository.save(MocksContact.buildContactEntity());
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void getContacts_statusOK() throws Exception {
        // Given:

        // When:
        var result = mockMvc.perform(get(PATH));

        // Then:
        result.andExpect(status().isBadRequest());
        // FUNCIONA

    }

    @Test
    void createNewContact() {
        // Given:
        // When:
        // Then:
    }

    @Test
    void createNewContact_With_BadRequest_Expect_ConstraintViolationException() {
        // Given:
        // When:
        // Then:
    }

}