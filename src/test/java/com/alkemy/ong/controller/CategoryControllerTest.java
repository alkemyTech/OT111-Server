package com.alkemy.ong.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
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


    @Test
    void getCategories() {

    }

    @Test
    @Query("INSERT INTO category ")
    void getAllBy_ShouldBeOK() throws Exception {
        //When
        var result = mockMvc.perform(get("/categories"));
        // Then
        result.andExpect(status().isOk());
        // Then
        result.andExpect(jsonPath("$[0].name").exists());
    }

    // Category POST sin nombre = 40x
    // DELETE hacer un GET y NOT FOUND (optional empty)
    // PUT notfound ID de PATH y en caso correcto: EXISTS cada atributo

}