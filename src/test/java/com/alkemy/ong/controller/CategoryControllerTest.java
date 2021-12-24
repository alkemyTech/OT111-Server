package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.utils.Mocks;
import com.alkemy.ong.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    private static final String PATH = "/categories";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    private CategoryEntity categorySaved;

    @BeforeEach
    void setUp() {
        categorySaved = categoryRepository.save(Mocks.buildCategoryEntity());
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void getCategoryDetails_statusOK() throws Exception {

        //Given

        //When
        var result = mockMvc.perform(get(PATH + "/{id}", categorySaved.getId()));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(categorySaved.getId()));
        result.andExpect(jsonPath("$.name").value(categorySaved.getName()));
        result.andExpect(jsonPath("$.description").value(categorySaved.getDescription()));
        result.andExpect(jsonPath("$.image").value(categorySaved.getImage()));
        result.andExpect(jsonPath("$.createdDate").exists());
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void getCategoryDetails_Expect_NotFound() throws Exception {

        //Given
        final long ID_NOT_FOUND = -1;

        //When
        var result = mockMvc.perform(get(PATH + "/{id}", ID_NOT_FOUND));

        //Then
        result.andExpect(status().isNotFound());
        result.andExpect(jsonPath("$.message").exists());
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void getAllPage_statusOK() throws Exception {

        //Given

        //When
        var result = mockMvc.perform(get(PATH));

        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.content[0].id").exists());
        result.andExpect(jsonPath("$.content[0].name").isNotEmpty());
        result.andExpect(jsonPath("$.pageable.pageNumber").value(0));
        result.andExpect(jsonPath("$.pageable.numberOfElements").exists());
        result.andExpect(jsonPath("$.pageable.totalElements").exists());
        result.andExpect(jsonPath("$.pageable.totalPages").exists());
    }

    @Test
    @WithMockUser(username = "userMock", roles = "USER")
    void getAllByCombo_statusOK() throws Exception {

        //Given

        //When
        var result = mockMvc.perform(get(PATH + "/by-combo"));

        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0]").exists());
    }


    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createNewCategory_statusOK() throws Exception {

        //Given
        var request = Mocks.buildCategoryRequest();

        //When
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").value(request.getName()));
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createNewCategory_With_BadRequest_Expect_Error() throws Exception {

        //Given
        var request = Mocks.buildCategoryRequestInvalid();

        //When
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors.length()").value(1));
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void updateCategory_statusOk() throws Exception {

        //Given
        final String NEW_NAME = "CATEGORY-NAME-23";
        var request = Mocks.buildCategoryRequest();
        request.setName(NEW_NAME);

        //When
        var result = mockMvc.perform(put(PATH + "/{id}", categorySaved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isNoContent());

        categoryRepository.flush();
        var entityUpdated = categoryRepository.findById(categorySaved.getId()).orElseThrow();
        then(entityUpdated.getName()).isEqualTo(NEW_NAME);
        then(entityUpdated.getModifiedDate()).isNotNull();
    }

    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void deleteCategoryById_statusOk() throws Exception {

        //Given
        var CATEGORY_ID = categorySaved.getId();

        //When
        var result = mockMvc.perform(delete(PATH + "/{id}", CATEGORY_ID));

        //Then
        result.andExpect(status().isNoContent());

        var entityDeleted = categoryRepository.findById(CATEGORY_ID);
        then(entityDeleted.isPresent()).isFalse();
    }

}