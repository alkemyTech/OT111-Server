package com.alkemy.ong.service;

import com.alkemy.ong.model.entity.ActivityEntity;

public interface ActivityService {
    ActivityEntity createActivity(String name, String content);
}
