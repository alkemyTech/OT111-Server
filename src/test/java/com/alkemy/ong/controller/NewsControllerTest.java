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
    private CategoryEntity categorySaved;

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

        //Then:
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.name").value(newsSaved.getName()));
        result.andExpect(jsonPath("$.content").value(newsSaved.getContent()));
        result.andExpect(jsonPath("$.image").value(newsSaved.getImage()));
        result.andExpect(jsonPath("$.category.id").value(newsSaved.getCategory().getId()));
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
        categoryRepository.flush();

        //Given:
        var request = NewsMocks.buildNewsRequest(categorySaved.getId());

        //When:
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        newsRepository.flush();

        //Then:
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.name").value(request.getName()));
        result.andExpect(jsonPath("$.content").value(request.getContent()));
        result.andExpect(jsonPath("$.image").value(request.getImage()));
        result.andExpect(jsonPath("$.category.id").value(categorySaved.getId()));
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createNews_Expect_BadRequest() throws Exception {

        //Given:
        var request = NewsMocks.buildNewsRequestInvalid(categorySaved.getId());

        //When:
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then:
        result.andExpect(status().isBadRequest());

    }

}