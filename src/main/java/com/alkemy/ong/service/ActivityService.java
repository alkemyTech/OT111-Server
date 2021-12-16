package com.alkemy.ong.service;

import com.alkemy.ong.model.request.ActivityRequestDTO;

public interface ActivityService {

    public void updateActivity(ActivityRequestDTO request, Long id);
}
