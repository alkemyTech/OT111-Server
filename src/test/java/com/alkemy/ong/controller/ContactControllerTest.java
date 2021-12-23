package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.ContactEntity;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.sendgrid.EmailServiceImpl;
import com.alkemy.ong.utils.MocksContact;
import com.alkemy.ong.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    private static final String PATH = "/contacts";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactRepository contactRepository;

    @MockBean
    private EmailServiceImpl emailServiceImpl;

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
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$").isNotEmpty());
        result.andExpect(jsonPath("$[0].id").exists());
        result.andExpect(jsonPath("$[0].name").isNotEmpty());
        result.andExpect(jsonPath("$[0].email").isNotEmpty());
        result.andExpect(jsonPath("$[0].message").isNotEmpty());

    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createNewContact() throws Exception {
        // Given:
        var request = MocksContact.buildContactRequest();

        // When:
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        // Then:
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").value(request.getName()));
        result.andExpect(jsonPath("$.email").value(request.getEmail()));
        result.andExpect(jsonPath("$.message").value(request.getMessage()));
        verify(emailServiceImpl, times(1)).sendContactConfirmation(request.getEmail());

    }

    @Test
    void createNewContact_With_BadRequest_Expect_ConstraintViolationException() throws Exception {
        // Given:
        var request = MocksContact.buildContactRequestInvalid();

        // When:
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        // Then:
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.message").value("Constraint Violations"));
        result.andExpect(jsonPath("$.errors.length()").value(3));

    }

}