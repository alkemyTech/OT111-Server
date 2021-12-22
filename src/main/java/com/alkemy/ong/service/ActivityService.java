package com.alkemy.ong.service;

import com.alkemy.ong.model.request.ActivityRequestDTO;
import com.alkemy.ong.model.request.ActivityUpdateRequestDTO;
import com.alkemy.ong.model.response.ActivityResponseDTO;
import com.alkemy.ong.model.response.ActivityUpdateResponseDTO;

public interface ActivityService {
    ActivityResponseDTO createActivity(ActivityRequestDTO request, String userName);
    ActivityUpdateResponseDTO updateActivity(ActivityUpdateRequestDTO request, Long id, String userName);
}
