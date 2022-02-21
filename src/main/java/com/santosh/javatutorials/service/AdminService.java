package com.santosh.javatutorials.service;

import com.santosh.javatutorials.entity.Topic;
import com.santosh.javatutorials.repository.AdminRepository;
import com.santosh.javatutorials.request.TopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public void addTopic(TopicDto request) {
        adminRepository.save(new Topic(request));
    }
}
