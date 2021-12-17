package com.alkemy.ong.controller;

import com.alkemy.ong.model.mapper.CategoryMapper;
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
class CategoryControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ObjectMapper objectMapper;

    //TODO: Me gustaria agregar un Query para INSERT una category y tomar un valor de este ultimo item.
    @Test
    void getCategories_statusOK() throws Exception {
        //When
        var result = mockMvc
                .perform(
                        get("/categories")
                                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
                );
        // Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[-1].name").value("TEST Category"));
    }

    //TODO: Agregar Query que meta una Entity con ID a pasar en /categoires/ID
    @Test
    void getCategoryDetails_statusOK() throws Exception {
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
        // Category POST , sin nombre = 40x
        // When
        var result = mockMvc
                .perform(
                        post("/categories")
                                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(categoryMapper.categoryEntity2DTO(Mocks.newCategory())))
                );
        // Then
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$").isNotEmpty());
        result.andExpect(jsonPath("$.name").value("Mock Category"));
    }

    // PUT notfound ID de PATH y en caso correcto: EXISTS cada atributo

    // DELETE hacer un GET y NOT FOUND (optional empty)

}