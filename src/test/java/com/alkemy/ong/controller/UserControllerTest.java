package com.alkemy.ong.controller;

import com.alkemy.ong.model.mapper.UserMapper;
import com.alkemy.ong.utils.Mocks;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUsers_success() throws Exception {
        var result = mockMvc
                .perform(get("/users")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYzMTE1MDQsImlhdCI6MTYzOTkxMTUwNH0.Rh8k3T4b1VwZ-9-JtWlLz94R4lt9lsj88FwV9QeQDFw"));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0].firstName").value("admin1"));
    }

    @Test
    void getAllUsers_fail() throws Exception {
        var result = mockMvc
                .perform(get("/users")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYzMTE1MDQsImlhdCI6MTYzOTkxMTUwNH0.Rh8k3T4b1VwZ-9-JtWlLz94R4lt9lsj88FwV9QeQDFw"));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[1].firstName").value("admin1"));
    }

    @Test
    void deleteUser() throws Exception {
        var result = mockMvc
                .perform(delete("/users/1"));
        result.andExpect(status().isNoContent());

    }

    @Test
    void getUserDetails_success() throws Exception {
        var result = mockMvc
                .perform(get("/users/10")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYzMTE1MDQsImlhdCI6MTYzOTkxMTUwNH0.Rh8k3T4b1VwZ-9-JtWlLz94R4lt9lsj88FwV9QeQDFw"));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.firstName").value("admin10"));
    }

    @Test
    void getUserDetails_fail() throws Exception {
        var result = mockMvc
                .perform(get("/users/10")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYzMTE1MDQsImlhdCI6MTYzOTkxMTUwNH0.Rh8k3T4b1VwZ-9-JtWlLz94R4lt9lsj88FwV9QeQDFw"));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.firstName").value("admin9"));
    }

    @Test
    void updateUser() throws Exception {
        var result = mockMvc
                .perform(get("/users/10")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYzMTE1MDQsImlhdCI6MTYzOTkxMTUwNH0.Rh8k3T4b1VwZ-9-JtWlLz94R4lt9lsj88FwV9QeQDFw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userMapper.entity2DTO(Mocks.updatedUser()))));

        result.andExpect(status().isOk());
        var toVerify = mockMvc.perform(
                get("/users/10")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYzMTE1MDQsImlhdCI6MTYzOTkxMTUwNH0.Rh8k3T4b1VwZ-9-JtWlLz94R4lt9lsj88FwV9QeQDFw")
        );
        toVerify.andExpect(jsonPath("$.firstName").value("Name updated"));
    }
}