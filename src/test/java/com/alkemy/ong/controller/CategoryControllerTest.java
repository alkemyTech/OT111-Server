package com.alkemy.ong.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
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
    @Rollback(false)
    @Transactional
    @Modifying
    @Query(
            value = "insert into category (id,name,description,image, created_date) values (999,'CategoryTest','CategoryTest','CategoryTest',now())",
            nativeQuery = true
    )
    void getCategories_statusOK() throws Exception {
        //When
        var result = mockMvc
                .perform(get("/categories")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjFAZW1haWwuY29tIiwiZXhwIjoxNjM5NzQ5NDEzLCJpYXQiOjE2Mzk2NjMwMTN9.wt7uW_4f6Go7CT53Kk1nKVXbFBcbQ6136oZQxkWI2xE"));
        // Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[-1].name").value("cat1"));
    }

    @Test
    @Query("INSERT INTO category (id,name,description, image) VALUES (1,'cat1','cat1','cat1' );")
    void getAllBy_ShouldBeOK() throws Exception {

    }


    // Category POST sin nombre = 40x
    // DELETE hacer un GET y NOT FOUND (optional empty)
    // PUT notfound ID de PATH y en caso correcto: EXISTS cada atributo

}