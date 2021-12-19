package com.alkemy.ong.service;

import com.alkemy.ong.model.request.ActivityUpdateRequestDTO;
import com.alkemy.ong.model.response.ActivityUpdateResponseDTO;

public interface ActivityService {

    ActivityUpdateResponseDTO updateActivity(ActivityUpdateRequestDTO request, Long id, String userName);
}
