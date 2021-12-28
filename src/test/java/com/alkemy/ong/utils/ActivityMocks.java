package com.alkemy.ong.utils;

import com.alkemy.ong.model.entity.ActivityEntity;
import com.alkemy.ong.model.request.ActivityRequest;

public class ActivityMocks {

    public static ActivityEntity buildActivityEntity() {
        return ActivityEntity.builder()
                .name("Mock Activity")
                .content("Mock Content")
                .image("Mock Image")
                .build();
    }

    public static ActivityRequest buildActivityRequest() {
        return ActivityRequest.builder()
                .name("Mock Activity")
                .content("Mock Content")
                .image("Mock Image")
                .build();
    }

    public static ActivityRequest buildActivityRequestInvalid() {
        return ActivityRequest.builder()
                .name("")
                .content("Mock Content")
                .image("Mock Image")
                .build();
    }

}
