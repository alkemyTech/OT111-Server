package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.OrganizationEntity;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.utils.OrganizationMocks;
import com.alkemy.ong.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
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

    private OrganizationEntity organizationSaved;

    @BeforeEach
    void setUp() {
        organizationSaved = organizationRepository.save(OrganizationMocks.buildOrganizationEntity());
    }


    // ** GET /organization/public ** TESTS
    @Test
    //@WithMockUser(username = "userMock", roles = "USER")
    @WithAnonymousUser
    void getOrganizationPublic_statusOK() throws Exception {

        //When
        var result = mockMvc.perform(get(PATH));

        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.name").value(organizationSaved.getName()));
        result.andExpect(jsonPath("$.image").value(organizationSaved.getImage()));
        result.andExpect(jsonPath("$.address").value(organizationSaved.getAddress()));
        result.andExpect(jsonPath("$.phone").value(organizationSaved.getPhone()));
        result.andExpect(jsonPath("$.facebookUrl").value(organizationSaved.getFacebookUrl()));
        result.andExpect(jsonPath("$.instagramUrl").value(organizationSaved.getInstagramUrl()));
        result.andExpect(jsonPath("$.linkedinUrl").value(organizationSaved.getLinkedinUrl()));
    }

    //If organization was deleted and try to update -> NOTFOUND
    @Test
    @WithMockUser(username = "userMock", roles = "USER")
    void getOrganizationPublic_Expected_NotFound() throws Exception {

        //Given
        organizationRepository.delete(organizationSaved);
        //When

        var result = mockMvc.perform(get(PATH));

        //Then
        result.andExpect(status().isNotFound());
        result.andExpect(jsonPath("$.message").exists());
    }

    // ** POST /organization/public ** TESTS
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

    //If name in request is invalid
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createOrganization_With_BadRequest_Expect_Invalid_Name() throws Exception {

        //Given
        var request = OrganizationMocks.buildOrganizationRequestInvalidName();

        //When
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors.length()").value(1));

    }

    //If image in request is invalid
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createOrganization_With_BadRequest_Expect_Invalid_Image() throws Exception {

        //Given
        var request = OrganizationMocks.buildOrganizationRequestInvalidImage();

        //When
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors.length()").value(1));

    }

    //If email in request is invalid
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createOrganization_With_BadRequest_Expect_Invalid_Email() throws Exception {

        //Given
        var request = OrganizationMocks.buildOrganizationRequestInvalidEmail();

        //When
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors.length()").value(1));
    }

    //If welcomeText in request is invalid
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createOrganization_With_BadRequest_Expect_Invalid_WelcomeText() throws Exception {

        //Given
        var request = OrganizationMocks.buildOrganizationRequestInvalidWelcomeText();

        //When
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors.length()").value(1));
    }


    // ** PUT /organization/public ** TESTS

    //PUT Tests with Repository
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void updateOrganization_statusOk() throws Exception {

        //Given
        final String NEW_NAME = "ORGANIZATION-NAME-12";
        var request = OrganizationMocks.buildOrganizationRequest();
        request.setName(NEW_NAME);

        //When
        var result = mockMvc.perform(put(PATH, organizationSaved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isOk());

        organizationRepository.flush();
        var entityUpdate = organizationRepository.findById(organizationSaved.getId()).orElseThrow();
        then(entityUpdate.getName()).isEqualTo(NEW_NAME);
        then(entityUpdate.getModifiedDate()).isNotNull();
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void updateOrganization_Expect_NotFound() throws Exception {

        //Given
        var ORGANIZATION_ID = organizationSaved.getId();


        //When
        organizationRepository.delete(organizationSaved);
        var result = mockMvc.perform(put(PATH, ORGANIZATION_ID));


        //Then
        result.andExpect(status().isNotFound());
        organizationRepository.flush();
        result.andExpect(jsonPath("$.message").exists());

    }

    //PUT test with response

    // ** DELETE /organization/public/{id} ** TESTS

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

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void deleteOrganizationById_Expect_NotFound() throws Exception {

        //Given
        final long ID_NOT_FOUND = -1L;

        //When
        var result = mockMvc.perform(delete(PATH + "/{id}", ID_NOT_FOUND));

        //Then
        result.andExpect(status().isNotFound());

        var entityDelete = organizationRepository.findById(ID_NOT_FOUND);
        then(entityDelete.isPresent()).isFalse();
    }


}
