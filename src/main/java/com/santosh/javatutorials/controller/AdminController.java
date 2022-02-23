package com.santosh.javatutorials.controller;

import com.santosh.javatutorials.request.MenuDto;
import com.santosh.javatutorials.request.TopicDto;
import com.santosh.javatutorials.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    IAdminService adminService;

    @PostMapping("/admin/add-topic")
    public String addTopic(@Valid @RequestBody TopicDto request) {
        adminService.addTopic(request);
        return "Topic Added Successfully";
    }

    @PostMapping("/add-menu")
    public String addMenu(@Valid @RequestBody MenuDto request) {
        adminService.addMenu(request);
        return "Menu Added Successfully";
    }

    @PutMapping("/update-topic/{id}")
    public String addTopic(@Valid @PathVariable Long id, @RequestBody TopicDto request) {
        adminService.updateTopic(id,request);
        return "Topic Updated Successfully";
    }

    @GetMapping("/topics")
    public List<TopicDto> topics() {
        return adminService.topics();
    }

    @GetMapping("/topic")
    public List<TopicDto> topics(@RequestParam("name") String name) {
        return adminService.topics(name);
    }

    @GetMapping("/topic/{id}")
    public TopicDto topics(@PathVariable Long id) {
        return adminService.topics(id);
    }
}
