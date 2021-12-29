package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.OrganizationEntity;
import com.alkemy.ong.model.entity.SlideEntity;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.utils.OrganizationMocks;
import com.alkemy.ong.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class OrganizationControllerTest {

    private static final String PATH = "/organization/public";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private SlideRepository slideRepository;

    private OrganizationEntity organizationSaved;
    private SlideEntity slideSaved;

    @BeforeEach
    void setUp() {

        organizationSaved = organizationRepository.save(OrganizationMocks.buildOrganizationEntity());
        slideSaved = slideRepository.save(OrganizationMocks.buildSlidesEntityWithOrganizationId(organizationSaved));
    }


    // ** GET /organization/public ** TESTS //

    //GET like normal user -> OK
    //TODO: Importante test fallando resolverlo
    @Test
    @WithMockUser(username = "userMock", roles = "USER")
    void getOrganizationPublic_statusOK() throws Exception {

        //When
        var result = mockMvc.perform(get(PATH));

        //Then
        organizationRepository.flush();
        slideRepository.flush();

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.name").value(organizationSaved.getName()));
        result.andExpect(jsonPath("$.image").value(organizationSaved.getImage()));
        result.andExpect(jsonPath("$.address").value(organizationSaved.getAddress()));
        result.andExpect(jsonPath("$.phone").value(organizationSaved.getPhone()));
        result.andExpect(jsonPath("$.facebookUrl").value(organizationSaved.getFacebookUrl()));
        result.andExpect(jsonPath("$.instagramUrl").value(organizationSaved.getInstagramUrl()));
        result.andExpect(jsonPath("$.linkedinUrl").value(organizationSaved.getLinkedinUrl()));

        result.andExpect(jsonPath("$.slides[0].text").value(slideSaved.getText()));
        result.andExpect(jsonPath("$.slides[0].imageUrl").value(slideSaved.getImageUrl()));
        result.andExpect(jsonPath("$.slides[0].order").value(slideSaved.getOrder()));

    }


    // ** POST /organization/public **

    //POST like ADMIN -> OK
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createOrganization_statusOK() throws Exception {
        //Given
        var request = OrganizationMocks.buildOrganizationRequest();
        //When
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));
        //Then
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").value(request.getName()));
        result.andExpect(jsonPath("$.image").value(request.getImage()));
        result.andExpect(jsonPath("$.address").value(request.getAddress()));
        result.andExpect(jsonPath("$.phone").value(request.getPhone()));
        result.andExpect(jsonPath("$.email").value(request.getEmail()));
        result.andExpect(jsonPath("$.welcomeText").value(request.getWelcomeText()));
        result.andExpect(jsonPath("$.aboutUsText").value(request.getAboutUsText()));
        result.andExpect(jsonPath("$.facebookUrl").value(request.getFacebookUrl()));
        result.andExpect(jsonPath("$.instagramUrl").value(request.getInstagramUrl()));
        result.andExpect(jsonPath("$.linkedinUrl").value(request.getLinkedinUrl()));

        organizationRepository.flush();
        var entityUpdated = organizationRepository.findById(organizationSaved.getId()).orElseThrow();
        then(entityUpdated.getCreatedDate()).isNotNull();
        then(entityUpdated.getCreatedBy()).isEqualTo("userMock");
    }

    //If is not a ADMIN role user. -> FORBIDDEN
    @Test
    @WithMockUser(username = "userMock", roles = "USER")
    void createOrganization_FORBIDEN() throws Exception {
        //Given
        var request = OrganizationMocks.buildOrganizationRequest();
        //When
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));
        //Then
        result.andExpect(status().isForbidden());
    }

    //If required fields in request are invalid -> BAD_REQUEST
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createOrganization_expectBadRequest_invalidFields() throws Exception {
        //Given
        var request = OrganizationMocks.buildOrganizationRequestInvalidFields();
        //When
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));
        //Then
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors.length()").value(4));
    }

    // ** PUT /organization/public ** TESTS

    //PUT Tests with Repository:

    //If try to update required fields -> OK!
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void updateOrganization_statusOk_updateRequiredFields() throws Exception {
        //Given
        final String NEW_NAME = "ORGANIZATION-UPDATE-NAME";
        final String NEW_IMAGE = "ORGANIZATION-UPDATE-IMAGE";
        final String NEW_EMAIL = "ORGANIZATION@UPDATE.COM";
        final String NEW_WELCOME_TEXT = "ORGANIZATION_UPDATE_WELCOME_TEXT";
        var request = OrganizationMocks.buildOrganizationRequest();
        request.setName(NEW_NAME);
        request.setImage(NEW_IMAGE);
        request.setEmail(NEW_EMAIL);
        request.setWelcomeText(NEW_WELCOME_TEXT);
        //When
        var result = mockMvc.perform(put(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));
        //Then
        result.andExpect(status().isOk());
        organizationRepository.flush();
        var entityUpdate = organizationRepository.findById(organizationSaved.getId()).orElseThrow();
        then(entityUpdate.getName()).isEqualTo(NEW_NAME);
        then(entityUpdate.getImage()).isEqualTo(NEW_IMAGE);
        then(entityUpdate.getEmail()).isEqualTo(NEW_EMAIL);
        then(entityUpdate.getWelcomeText()).isEqualTo(NEW_WELCOME_TEXT);
        then(entityUpdate.getModifiedDate()).isNotNull();
        //then(entityUpdate.getModifiedBy()).isEqualTo("userMock");
    }

    //PUT test with response
    //If try to update required fields -> OK and response
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void updateOrganizationRequiredFields_statusOK_Response() throws Exception {
        //Given
        final String NEW_NAME = "ORGANIZATION-UPDATE-NAME";
        final String NEW_IMAGE = "ORGANIZATION-UPDATE-IMAGE";
        final String NEW_EMAIL = "ORGANIZATION@UPDATE.COM";
        final String NEW_WELCOME_TEXT = "ORGANIZATION_UPDATE_WELCOME_TEXT";
        var request = OrganizationMocks.buildOrganizationRequest();
        request.setName(NEW_NAME);
        request.setImage(NEW_IMAGE);
        request.setEmail(NEW_EMAIL);
        request.setWelcomeText(NEW_WELCOME_TEXT);
        //When
        var result = mockMvc.perform(put(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));
        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").value(NEW_NAME));
        result.andExpect(jsonPath("$.image").value(NEW_IMAGE));
        result.andExpect(jsonPath("$.address").value(request.getAddress()));
        result.andExpect(jsonPath("$.phone").value(request.getPhone()));
        result.andExpect(jsonPath("$.email").value(NEW_EMAIL));
        result.andExpect(jsonPath("$.welcomeText").value(NEW_WELCOME_TEXT));
        result.andExpect(jsonPath("$.aboutUsText").value(request.getAboutUsText()));
        result.andExpect(jsonPath("$.facebookUrl").value(request.getFacebookUrl()));
        result.andExpect(jsonPath("$.instagramUrl").value(request.getInstagramUrl()));
        result.andExpect(jsonPath("$.linkedinUrl").value(request.getLinkedinUrl()));

        organizationRepository.flush();
        var entityUpdated = organizationRepository.findById(organizationSaved.getId()).orElseThrow();
        then(entityUpdated.getModifiedDate()).isNotNull();
        then(entityUpdated.getCreatedBy()).isEqualTo("userMock");
        //then(entityUpdated.getModifiedBy()).isEqualTo("userMock");
    }

    //update Given Invalid required fields -> BAD_REQUEST
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void updateOrganization_expectBadRequest_invalidFields() throws Exception {
        //Given
        var request = OrganizationMocks.buildOrganizationRequestInvalidFields();
        //When
        var result = mockMvc.perform(put(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));
        //Then
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors.length()").value(4));
    }

    // ** DELETE /organization/public/{id} ** TESTS

    //DELETE organization like ADMIN -> OK
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void deleteOrganizationById_statusOk() throws Exception {
        //Given
        var ORGANIZATION_ID = organizationSaved.getId();
        //When
        var result = mockMvc.perform(delete(PATH + "/{id}", ORGANIZATION_ID));
        //Then
        result.andExpect(status().isNoContent());
        var entityDelete = organizationRepository.findById(ORGANIZATION_ID);
        then(entityDelete.isPresent()).isFalse();
    }

    //DELETE organization ID not existent -> NOTFOUND
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void deleteOrganizationById_Expect_NotFound() throws Exception {
        //Given
        final long ID_NOT_FOUND = -1L;
        //When
        var result = mockMvc.perform(delete(PATH + "/{id}", ID_NOT_FOUND));
        //Then
        result.andExpect(status().isNotFound());
        result.andExpect(jsonPath("$.message").exists());
    }


}
