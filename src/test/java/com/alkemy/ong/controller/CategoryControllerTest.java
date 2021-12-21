package com.alkemy.ong.controller;

import com.alkemy.ong.model.mapper.CategoryMapper;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.utils.Mocks;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
class CategoryControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        categoryRepository.save(Mocks.newCategory());
    }

    // =================
    // ==== Success ====
    // =================

    @Test
    void getCategories_statusOK() throws Exception {
        var result = mockMvc
                .perform(
                        get("/categories")
                                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
                );
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.content[-1].name").value("Mock Category"));
    }

    @Test
    void getCategoryDetails_statusOK() throws Exception {
        // TODO: Como agregar una Entity con ID forzado? Para luego Buscarla en "/categories/id".
        // 17 existe en mi DB.
        var result = mockMvc
                .perform(
                        get("/categories/17")
                                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
                );
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.name").value("TEST Category"));
    }

    @Test
    void createNewCategory_statusOK() throws Exception {
        var result = mockMvc
                .perform(
                        post("/categories")
                                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(categoryMapper.categoryEntity2DTO(Mocks.newCategory())))
                );
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$").isNotEmpty());
        result.andExpect(jsonPath("$.name").value("Mock Category"));
    }

    @Test
    void updateCategory_statusOk() throws Exception {
        // TODO: Como agregar una Entity con ID forzado? Para luego UPDATE en "/categories/id".
        var result = mockMvc
                .perform(
                        put("/categories/17")
                                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(categoryMapper.categoryEntity2DTO(Mocks.updatedCategory())))
                );
        result.andExpect(status().isOk());
        var toVerify = mockMvc.perform(
                get("/categories/17")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
        );
        toVerify.andExpect(jsonPath("$.name").value("Updated Category"));
    }

    @Test
    void deleteCategoryById_statusNotFound() throws Exception {
        // TODO: Como agregar una Entity con ID forzado? Para luego DELETE en "/categories/id".
        var result = mockMvc
                .perform(
                        delete("/categories/17")
                                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
                );
        result.andExpect(status().isNoContent());
        var toVerify = mockMvc.perform(
                get("/categories/17")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
        );
        toVerify.andExpect(status().isNotFound());
    }

    // ====================
    // ==== Exceptions ====
    // ====================

    @Test
    void getCategoryDetails_noSuchElementException() throws Exception {
        // TODO: Como buscar un ID Inexistente?
        var result = mockMvc
                .perform(
                        get("/categories/17000")
                                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
                );
        result.andExpect(status().isNotFound());
        result.andExpect(jsonPath("$.message").value("No value present"));
    }

    @Test
    void createNewCategory_constraintViolationException() throws Exception {
        var result = mockMvc
                .perform(
                        post("/categories")
                                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(Mocks.categoryEntityWithConstraintViolation()))
                );
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors").isNotEmpty());
    }

}