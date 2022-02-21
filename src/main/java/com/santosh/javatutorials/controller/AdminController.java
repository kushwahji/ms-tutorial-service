package com.santosh.javatutorials.controller;

import com.santosh.javatutorials.request.TopicDto;
import com.santosh.javatutorials.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    IAdminService IAdminService;

    @PostMapping("/add-topic")
    public String addTopic(@RequestBody TopicDto request){
        IAdminService.addTopic(request);
        return "Topic Added Successfully";
    }
}
