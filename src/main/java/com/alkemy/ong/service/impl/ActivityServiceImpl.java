package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.request.ActivityRequestDTO;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public void updateActivity(ActivityRequestDTO request, Long id) {
        ActivityEntity foundActivity = activityRepository.findById(id).orElseThrow();
        foundActivity.setName(request.getName());
        foundActivity.setContent(request.getContent());
        foundActivity.setImage(request.getImage());
        activityRepository.save(foundActivity);
    }
}
