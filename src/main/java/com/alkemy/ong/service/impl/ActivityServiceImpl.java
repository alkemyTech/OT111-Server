package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.request.ActivityUpdateRequestDTO;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserAuthService userAuthServ;

    @Override
    public void updateActivity(ActivityUpdateRequestDTO request, Long id,  String userName) {

        OffsetDateTime updateTime = OffsetDateTime.now();

        ActivityEntity foundActivity = activityRepository.findById(id).orElseThrow();
        foundActivity.setName(request.getName());
        foundActivity.setContent(request.getContent());
        foundActivity.setImage(request.getImage());
        foundActivity.setModifiedDate(updateTime);
        foundActivity.setModifiedBy(userName);


        activityRepository.save(foundActivity);
    }
}
