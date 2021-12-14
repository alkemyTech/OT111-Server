package com.alkemy.ong.service;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.request.ActivityRequestDTO;
import com.alkemy.ong.model.response.ActivityResponseDTO;

public interface ActivityService {
    ActivityResponseDTO createActivity(ActivityRequestDTO request);
}
