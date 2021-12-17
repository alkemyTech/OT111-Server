package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.mapper.ActivityMapper;
import com.alkemy.ong.model.request.ActivityRequestDTO;
import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.ActivityResponseDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;


    public ActivityResponseDTO createActivity(ActivityRequestDTO request){
        ActivityEntity newActivity = ActivityMapper.activityDTO2Entity(request);
        ActivityEntity savedActivity = activityRepository.save(newActivity);
        return ActivityMapper.activityEntity2DTO(savedActivity);
    }
}
