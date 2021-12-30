package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.auth.service.UserDetailsCustomService;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.request.security.RegisterRequest;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.utils.Mocks;
import com.alkemy.ong.utils.MocksAuth;
import com.alkemy.ong.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class UserAuthControllerTest {

    private static final String PATH = "/auth";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private UserAuthService userAuthServ;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void signUp() throws Exception {

        var request = MocksAuth.buildRegisterRequest();

        //When
        var result = mockMvc.perform(post(PATH + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.firstName").value(request.getFirstName()));
        result.andExpect(jsonPath("$.lastName").value(request.getLastName()));
        result.andExpect(jsonPath("$.jwt").isNotEmpty());


    }

    @Test
    void login() throws Exception {

        var usuario = userDetailsCustomService.signupUser(MocksAuth.buildRegisterRequest());

        //TODO userRepository.flush();  Body = {"message":"A granted authority textual representation is required","httpStatus":400,"timestamp":"2021-12-27T17:51:27.61046-03:00"}

        var request = MocksAuth.buildAuthRequest();

        var result = mockMvc.perform(post(PATH + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.email").value(request.getUsername()));
        result.andExpect(jsonPath("$.jwt").isNotEmpty());

    }
    @Test
    void login_Invalid() throws Exception {

        var usuario = userDetailsCustomService.signupUser(MocksAuth.buildRegisterRequest());

        //TODO userRepository.flush();  Body = {"message":"A granted authority textual representation is required","httpStatus":400,"timestamp":"2021-12-27T17:51:27.61046-03:00"}

        var request = MocksAuth.buildAuthRequestInvalid();

        var result = mockMvc.perform(post(PATH + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isForbidden());
        result.andExpect(jsonPath("$.message").value("Bad credentials"));

    }

    @Test
    void meData() throws Exception {

        var usuario = userAuthServ.loginAttempt(MocksAuth.buildAuthRequest());

        var result = mockMvc.perform(get(PATH + "/me")
                .header("Authorization", "Bearer " + usuario.getJwt()));

        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.firstName").exists());
        result.andExpect(jsonPath("$.lastName").exists());
        result.andExpect(jsonPath("$.email").value(usuario.getEmail()));
        result.andExpect(jsonPath("$.photo").exists());

    }

    @Test
    void meData_Invalid() throws Exception {

        var usuario = userAuthServ.loginAttempt(MocksAuth.buildAuthRequest());

        var result = mockMvc.perform(get(PATH + "/me"));
                //.header("Authorization", "Bearer " + usuario.getJwt()));

        //Then
        result.andExpect(status().isNotFound());
        result.andExpect(jsonPath("$.httpStatus").value("404"));

    }
}