package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.MemberEntity;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.utils.MemberMocks;
import com.alkemy.ong.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    private static final String PATH = "/members";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    private MemberEntity memberSaved;

    @BeforeEach
    void setUp() {
        memberSaved = memberRepository.save(MemberMocks.buildMemberEntity());
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void getAllMembersPage_statusOK() throws Exception {
        //Given

        //When
        var result = mockMvc.perform(get(PATH));
        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.content[0].id").exists());
        result.andExpect(jsonPath("$.content[0].name").isNotEmpty());
        result.andExpect(jsonPath("$.pageable.pageNumber").value(0));
        result.andExpect(jsonPath("$.pageable.numberOfElements").exists());
        result.andExpect(jsonPath("$.pageable.totalElements").exists());
        result.andExpect(jsonPath("$.pageable.totalPages").exists());
    }

    @Test
    @WithMockUser(username = "userMock", roles = "USER")
    void createNewMember_statusOK() throws Exception {
        //Given
        var request = MemberMocks.buildMemberRequest();
        //When
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));
        //Then
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").value(request.getName()));
        result.andExpect(jsonPath("$.image").value(request.getImage()));
        result.andExpect(jsonPath("$.description").value(request.getDescription()));
        result.andExpect(jsonPath("$.facebookUrl").value(request.getFacebookUrl()));
        result.andExpect(jsonPath("$.instagramUrl").value(request.getInstagramUrl()));
        result.andExpect(jsonPath("$.linkedinUrl").value(request.getLinkedinUrl()));
    }

    @Test
    @WithMockUser(username = "userMock", roles = "USER")
    void createNewMember_With_BadRequest_Expect_Error() throws Exception {
        //Given
        var request = MemberMocks.buildMemberRequestInvalidFields();
        //When
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));
        //Then
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors.length()").value(2));
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void updateMember_statusOK() throws Exception {
        //Given
        final String NEW_NAME = "NEW MEMBER NAME";
        final String NEW_IMAGE = "NEW MEMBER IMAGE";
        var request = MemberMocks.buildMemberRequest();
        request.setName(NEW_NAME);
        request.setImage(NEW_IMAGE);
        //When
        var result = mockMvc.perform(put(PATH + "/{id}", memberSaved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));
        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").value(NEW_NAME));
        result.andExpect(jsonPath("$.image").value(NEW_IMAGE));
        result.andExpect(jsonPath("$.description").value(request.getDescription()));
        result.andExpect(jsonPath("$.facebookUrl").value(request.getFacebookUrl()));
        result.andExpect(jsonPath("$.instagramUrl").value(request.getInstagramUrl()));
        result.andExpect(jsonPath("$.linkedinUrl").value(request.getLinkedinUrl()));

        memberRepository.flush();
        var entityUpdated = memberRepository.findById(memberSaved.getId()).orElseThrow();
        then(entityUpdated.getModifiedDate()).isNotNull();
        then(entityUpdated.getCreatedBy()).isEqualTo("userMock");
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void updateMember_expectBadRequest_invalidFields() throws Exception {
        //Given
        var request = MemberMocks.buildMemberRequestInvalidFields();
        //When
        var result = mockMvc.perform(put(PATH + "/{id}", memberSaved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));
        //Then
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors.length()").value(2));
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void deleteMember_statusOk() throws Exception {
        //Given
        var MEMBER_ID = memberSaved.getId();
        //When
        var result = mockMvc.perform(delete(PATH + "/{id}", MEMBER_ID));
        //Then
        result.andExpect(status().isNoContent());
        var entityDeleted = memberRepository.findById(MEMBER_ID);
        then(entityDeleted.isPresent()).isFalse();
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void deleteMember_Expect_NotFound() throws Exception {
        //Given
        final long ID_NOT_FOUND = -1L;
        //When
        var result = mockMvc.perform(delete(PATH + "/{id}", ID_NOT_FOUND));
        //Then
        result.andExpect(status().isNotFound());
        result.andExpect(jsonPath("$.message").exists());
    }
}
