package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.entity.NewsEntity;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.utils.NewsMocks;
import com.alkemy.ong.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
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
<<<<<<< HEAD

    @BeforeEach
    void setUp() {


        CategoryEntity categorySaved = categoryRepository.save(NewsMocks.buildCategoryEntity());

=======
    private CategoryEntity categorySaved;

    @BeforeEach
    void setUp() {
        categorySaved = categoryRepository.save(NewsMocks.buildCategoryEntity());
>>>>>>> f78cb153c07c41f1d75805da12fd111897ab7ec9
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
<<<<<<< HEAD

=======
>>>>>>> f78cb153c07c41f1d75805da12fd111897ab7ec9
        //Given

        //When
        var result = mockMvc.perform(get(PATH + "/{id}", newsSaved.getId()));

        //Then:
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.name").value(newsSaved.getName()));
        result.andExpect(jsonPath("$.content").value(newsSaved.getContent()));
        result.andExpect(jsonPath("$.image").value(newsSaved.getImage()));
        result.andExpect(jsonPath("$.category.id").value(newsSaved.getCategory().getId()));
<<<<<<< HEAD

=======
>>>>>>> f78cb153c07c41f1d75805da12fd111897ab7ec9
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void findById_Expect_NotFound() throws Exception {

        //Given
        final long ID_NOT_FOUND = -1;
        //When
        var result = mockMvc.perform(get(PATH + "/{id}", ID_NOT_FOUND));

        //Then:
        result.andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createNews_statusCreated() throws Exception {
<<<<<<< HEAD
        //Given:
        var request = NewsMocks.buildNewsRequest();
=======
        categoryRepository.flush();

        //Given:
        var request = NewsMocks.buildNewsRequest(categorySaved.getId());

>>>>>>> f78cb153c07c41f1d75805da12fd111897ab7ec9
        //When:
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));
<<<<<<< HEAD
=======

        newsRepository.flush();

>>>>>>> f78cb153c07c41f1d75805da12fd111897ab7ec9
        //Then:
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.name").value(request.getName()));
        result.andExpect(jsonPath("$.content").value(request.getContent()));
        result.andExpect(jsonPath("$.image").value(request.getImage()));
<<<<<<< HEAD
        result.andExpect(jsonPath("$.category.id").value(request.getCategoryId()));
=======
        result.andExpect(jsonPath("$.category.id").value(categorySaved.getId()));
>>>>>>> f78cb153c07c41f1d75805da12fd111897ab7ec9
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createNews_Expect_BadRequest() throws Exception {

        //Given:
<<<<<<< HEAD
        var request = NewsMocks.buildNewsRequestInvalid();
=======
        var request = NewsMocks.buildNewsRequestInvalid(categorySaved.getId());
>>>>>>> f78cb153c07c41f1d75805da12fd111897ab7ec9

        //When:
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then:
        result.andExpect(status().isBadRequest());

    }

}