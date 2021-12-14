package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.request.ActivityRequestDTO;
import com.alkemy.ong.model.response.ActivityResponseDTO;
import com.alkemy.ong.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl {
    @Autowired
    private ActivityRepository activityRepository;

    public ActivityResponseDTO createActivity(ActivityRequestDTO request){
        ActivityEntity newCategory = categoryMapper.categoryDTO2Entity(request);
        ActivityEntity savedCategory = categoryRepository.save(newCategory);
        return categoryMapper.categoryEntity2DTO(savedCategory);
    }
}
