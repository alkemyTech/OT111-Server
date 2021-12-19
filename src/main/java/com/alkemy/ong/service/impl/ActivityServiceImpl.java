package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.mapper.ActivityMapper;
import com.alkemy.ong.model.request.ActivityRequestDTO;
import com.alkemy.ong.model.request.ActivityUpdateRequestDTO;
import com.alkemy.ong.model.response.ActivityResponseDTO;
import com.alkemy.ong.model.response.ActivityUpdateResponseDTO;
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
    private ActivityMapper activityMapper;


    public ActivityResponseDTO createActivity(ActivityRequestDTO request){
        ActivityEntity newActivity = ActivityMapper.activityDTO2Entity(request);
        ActivityEntity savedActivity = activityRepository.save(newActivity);
        return ActivityMapper.activityEntity2DTO(savedActivity);
    }

    @Override
    public ActivityUpdateResponseDTO updateActivity(ActivityUpdateRequestDTO request, Long id, String userName) {

        OffsetDateTime updateTime = OffsetDateTime.now();

        ActivityEntity foundActivity = activityRepository.findById(id).orElseThrow();
        foundActivity.setName(request.getName());
        foundActivity.setContent(request.getContent());
        foundActivity.setImage(request.getImage());
        foundActivity.setModifiedDate(updateTime);
        foundActivity.setModifiedBy(userName);


        ActivityEntity saved = activityRepository.save(foundActivity);

        return activityMapper.activityUpdateEntity2DTO(saved);
    }
}
