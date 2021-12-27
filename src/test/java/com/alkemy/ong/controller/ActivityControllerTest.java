package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.request.ActivityRequest;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.utils.ActivityMocks;
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

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class ActivityControllerTest {

    private static final String PATH = "/activities";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ActivityRepository activityRepository;

    private ActivityEntity savedActivity;

    @BeforeEach
    void setUp() { savedActivity = activityRepository.save(ActivityMocks.buildActivityEntity());
    }

    //Admin POST Activity -> Ok
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createActivity_statusOK() throws Exception {

        //Given:
        ActivityRequest request = ActivityMocks.buildActivityRequest();

        //When:
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then:
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").value(request.getName()));
        result.andExpect(jsonPath("$.content").value(request.getContent()));
        activityRepository.flush();
        ActivityEntity createdEntity = activityRepository.findById(savedActivity.getId()).orElseThrow();
        then(createdEntity.getCreatedDate()).isNotNull();
        then(createdEntity.getCreatedBy()).isEqualTo("userMock");

    }

    // ADMIN POST Activity Bad Request -> Ok
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void createActivity_with_Bad_Request() throws Exception {

        //Given:
        ActivityRequest request = ActivityMocks.buildActivityRequestInvalid();

        //When
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors.length()").value(1));

    }

    //Admin UPDATE Activity -> Ok
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void updateActivity_statusOK() throws Exception{

        //Given:
        final String NEW_NAME = "Activity-NAME";
        ActivityRequest request = ActivityMocks.buildActivityRequest();
        request.setName(NEW_NAME);

        //When:
        var result = mockMvc.perform(put(PATH + "/{id}",savedActivity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then:
        result.andExpect(status().isNoContent());

        activityRepository.flush();
        ActivityEntity createdEntity = activityRepository.findById(savedActivity.getId()).orElseThrow();
        then(createdEntity.getName()).isEqualTo(NEW_NAME);
        then(createdEntity.getModifiedDate()).isNotNull();

    }

    //Admin UPDATE Activity Bad Request -> Ok
    @Test
    @WithMockUser(username = "userMock", roles = "ADMIN")
    void updateActivity_with_Bad_Request() throws Exception {

        //Given:
        ActivityRequest request = ActivityMocks.buildActivityRequestInvalid();

        //When
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors.length()").value(1));

    }



    //User POST Activity Forbidden
    @Test
    @WithMockUser(username = "userMock", roles = "USER")
    void createActivity_Forbidden() throws Exception {

        //Given:
        ActivityRequest request = ActivityMocks.buildActivityRequest();

        //When:
        var result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then
        result.andExpect(status().isForbidden());
    }

    //User UPDATE Activity Forbidden
    @Test
    @WithMockUser(username = "userMock", roles = "USER")
    void updateActivity_Forbidden() throws Exception{

        //Given:
        final String NEW_NAME = "Activity-NAME";
        ActivityRequest request = ActivityMocks.buildActivityRequest();
        request.setName(NEW_NAME);

        //When:
        var result = mockMvc.perform(put(PATH + "/{id}",savedActivity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(request)));

        //Then:
        result.andExpect(status().isForbidden());

    }
}