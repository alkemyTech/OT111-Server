package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl {
    @Autowired
    private ActivityRepository activityRepository;

    public void createActivity(String name, String content){


    }
}
