package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.entity.NewsEntity;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.utils.NewsMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class NewsControllerTest {

    private static final String PATH = "/news";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private NewsEntity newsSaved;

    private CategoryEntity categorySaved;

    NewsControllerTest() {
    }


    @BeforeEach
    void setUp() {


        categorySaved = categoryRepository.save(NewsMocks.buildCategoryEntity());

        newsSaved = newsRepository.save(NewsEntity.builder()
                .name("MockNews name")
                .content("MockNews content")
                .image("MockNews image")
                .category(categorySaved)
                .build());
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void findById_statusOK() throws Exception {

        //Given

        //When
        var result = mockMvc.perform(get(PATH + "/{id}", newsSaved.getId()));

        result.andExpect(status().isOk());
        //result.andExpect(jsonPath("$.id").value(newsSaved.getId()));
        result.andExpect(jsonPath("$.name").value(newsSaved.getName()));
        result.andExpect(jsonPath("$.content").value(newsSaved.getContent()));
        result.andExpect(jsonPath("$.image").value(newsSaved.getImage()));
        result.andExpect(jsonPath("$.category.id").value(newsSaved.getCategory().getId()));
        //result.andExpect(jsonPath("$.category").exists());

    }

    @Test
    void findById_Expect_NotFound() {
    }

    @Test
    void createNews_statusCreated() {
    }

    @Test
    void createNews_Except_BadRequest() {
    }

    @Test
    void getNewsPage_statusOK() {
    }

    @Test
    void getNewsPage_Expect_NotFound() {
    }

    @Test
    void updateNews_statusOK() {
    }

    @Test
    void updateNews_Expected_NotFound() {
    }

    @Test
    void deleteNewsById_statusOK() {
    }

    @Test
    void deleteNewsById_Expected_NotFound() {
    }
}