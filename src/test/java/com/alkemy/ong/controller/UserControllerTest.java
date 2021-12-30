package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.RoleEntity;
import com.alkemy.ong.model.entity.UserEntity;

import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.utils.MocksUser;
import com.alkemy.ong.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private static final String PATH = "/users";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private UserEntity userSaved;

    @BeforeEach
    void setUp() {
        userSaved = userRepository.save(MocksUser.buildUserEntity());
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void getAllUsers_success() throws Exception {

        var result = mockMvc.perform(get(PATH));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0]").exists());
    }

    @Test
    @WithMockUser(username = "userMock", roles = "USER")
    void getAllUsers_fail() throws Exception {

        var result = mockMvc.perform(get(PATH));

        result.andExpect(status().isForbidden());
        result.andExpect(jsonPath("$[999]").doesNotExist());
    }

    @Test
    @WithMockUser(username = "userMock@mail.com", roles = "USER")
    void deleteUser_success() throws Exception {

        var result = mockMvc.perform(delete(PATH + "/{id}", userSaved.getId()));

        result.andExpect(status().isNoContent());
        var entityDelete= userRepository.findById(userSaved.getId());
        then(entityDelete.isPresent()).isFalse();
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void deleteUser_fail() throws Exception {

        var result = mockMvc.perform(delete(PATH + "/{id}", userSaved.getId()));

        result.andExpect(status().isForbidden());
        var entityDelete= userRepository.findById(userSaved.getId());
        then(entityDelete.isPresent()).isTrue();
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void getUserDetails_success() throws Exception {

        var result = mockMvc.perform(get(PATH + "/{id}", userSaved.getId()));

        List<RoleEntity> roles = (List) userSaved.getRoles();

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(userSaved.getId()));
        result.andExpect(jsonPath("$.firstName").value(userSaved.getFirstName()));
        result.andExpect(jsonPath("$.lastName").value(userSaved.getLastName()));
        result.andExpect(jsonPath("$.email").value(userSaved.getEmail()));
        result.andExpect(jsonPath("$.photo").value(userSaved.getPhoto()));
        result.andExpect(jsonPath("$.roles[0].name").value(roles.get(0).getName()));
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void getUserDetails_fail() throws Exception {

        final long ID_NOT_FOUND = -1;

        var result = mockMvc.perform(get(PATH + "/{id}", ID_NOT_FOUND));

        //Then
        result.andExpect(status().isNotFound());
        result.andExpect(jsonPath("$.message").exists());
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void updateUser_success() throws Exception {

        //Given
        final String NEW_NAME = "Mock USER 99";
        var request = MocksUser.buildUserRequest();
        request.setFirstName(NEW_NAME);
        //When
        var result = mockMvc.perform(put(PATH + "/{id}", userSaved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isOk());

        userRepository.flush();
        var entityUpdated = userRepository.findById(userSaved.getId()).orElseThrow();
        then(entityUpdated.getFirstName()).isEqualTo(NEW_NAME);
        then(entityUpdated.getModifiedDate()).isNotNull();
    }
}