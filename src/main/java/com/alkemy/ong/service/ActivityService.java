package com.alkemy.ong.service;

import com.alkemy.ong.model.request.ActivityUpdateRequestDTO;

public interface ActivityService {

    void updateActivity(ActivityUpdateRequestDTO request, Long id, String userName);
}
