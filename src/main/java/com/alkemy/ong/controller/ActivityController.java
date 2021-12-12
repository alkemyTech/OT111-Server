package com.alkemy.ong.controller;

import com.alkemy.ong.service.sendgrid.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

    @Autowired
    ActivityController activityController;

    @GetMapping("/activities/{name}/{content}")
    public String createActivity(@PathVariable String name, String content)
    {
        return	activityController;
    }
}
