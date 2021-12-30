package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.mapper.ActivityMapper;
import com.alkemy.ong.model.request.ActivityRequest;
import com.alkemy.ong.model.request.ActivityUpdateRequestDTO;
import com.alkemy.ong.model.response.ActivityResponseDTO;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    private final ActivityMapper activityMapper;

  
    @Override
    public ActivityResponseDTO createActivity(ActivityRequest request){
        ActivityEntity newActivity = ActivityMapper.activityDTO2Entity(request);
        ActivityEntity savedActivity = activityRepository.save(newActivity);
        return ActivityMapper.activityEntity2DTO(savedActivity);
    }

    @Override
    public ActivityResponseDTO updateActivity(ActivityUpdateRequestDTO request, Long id) {

        ActivityEntity foundActivity = activityRepository.findById(id).orElseThrow();
        foundActivity.setName(request.getName());
        foundActivity.setContent(request.getContent());
        foundActivity.setImage(request.getImage());

        ActivityEntity saved = activityRepository.save(foundActivity);

        return activityMapper.activityEntity2DTO(saved);
    }
}
