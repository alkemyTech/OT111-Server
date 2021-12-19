package com.alkemy.ong.controller;

import com.alkemy.ong.model.mapper.CategoryMapper;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.utils.Mocks;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
        result.andExpect(jsonPath("$.content[-1].name").value("TEST Category"));
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

    // TODO: Como puedo forzar una busqueda a algo inexistente sin Mock?
    @Test
    void getCategoryDetails_noSuchElementException() throws Exception {
        var result = mockMvc
                .perform(
                        get("/categories/17000")
                                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
                );
//        result.andExpect(status().isNotFound());
        result.andExpect(jsonPath("$.message").value("No value presentttt"));
        result.andExpect(status().isOk());
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

    //TODO: Me gustaria crear una Category la cual modificar por ID (Query)
    @Test
    void updateCategory_statusOk() throws Exception {
        // PUT notfound ID de PATH y en caso correcto: EXISTS cada atributo
        // When
        var result = mockMvc
                .perform(
                        put("/categories/17")
                                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(categoryMapper.categoryEntity2DTO(Mocks.updatedCategory())))
                );
        // Then
        result.andExpect(status().isOk());
        var toVerify = mockMvc.perform(
                get("/categories/17")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJbm1vcnRhbEBlbWFpbC5jb20iLCJleHAiOjE3MjYxNDI0NTQsImlhdCI6MTYzOTc0MjQ1NH0.INSQ84JDdLwviHHa3RXDLv1V2wJlKk_6OMVPuQ5PCM4")
        );
        toVerify.andExpect(jsonPath("$.name").value("Updated Category"));
    }

    //TODO: Me gustaria crear una, para luego eliminarla por ID
    @Test
    void deleteCategoryById_statusNotFound() throws Exception {
        // DELETE hacer un GET y NOT FOUND (optional empty)
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
//        toVerify.andExpect(status().isOk());
        toVerify.andExpect(status().isNotFound());
    }

}