package com.alkemy.ong.service;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.request.ActivityRequestDTO;
import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.ActivityResponseDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;

public interface ActivityService {
    static ActivityResponseDTO createActivity(ActivityRequestDTO request);
}
