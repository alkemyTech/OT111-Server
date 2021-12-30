package com.alkemy.ong.service;

import com.alkemy.ong.model.request.ActivityRequest;
import com.alkemy.ong.model.request.ActivityUpdateRequestDTO;
import com.alkemy.ong.model.response.ActivityResponseDTO;

public interface ActivityService {
    ActivityResponseDTO createActivity(ActivityRequest request);
    ActivityResponseDTO updateActivity(ActivityUpdateRequestDTO request, Long id);
}
