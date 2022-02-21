package com.santosh.javatutorials.service;

import com.santosh.javatutorials.entity.Topic;
import com.santosh.javatutorials.exception.MsApplicationException;
import com.santosh.javatutorials.repository.AdminRepository;
import com.santosh.javatutorials.request.TopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService implements IAdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public void addTopic(TopicDto request) {
        adminRepository.save(new Topic(request));
    }

    @Override
    public List<TopicDto> topics() {
        return Optional.ofNullable(adminRepository.findAll().stream().map(TopicDto::new).collect(Collectors.toList())).orElseThrow(() -> new MsApplicationException("record not found", "record not found"));
    }

    @Override
    public List<TopicDto> topics(String name) {
        return Optional.ofNullable(adminRepository.findByNameContaining(name).stream().map(TopicDto::new).collect(Collectors.toList())).orElseThrow(() -> new MsApplicationException("record not found", "record not found for " + name));
    }

    @Override
    public TopicDto topics(Long id) {
        return new TopicDto(Optional.ofNullable(adminRepository.findById(id).get()).orElseThrow(() -> new MsApplicationException("record not found", "record not found for " + id)));
    }

    @Override
    public void updateTopic(Long id, TopicDto request) {

        if (adminRepository.existsById(id)) {
            TopicDto topic = topics(id);
            Optional.ofNullable(topic).orElseThrow(() -> new MsApplicationException("record not found", "record not found for this id" + id));
            topic.setName(request.getName());
            adminRepository.save(new Topic(topic));
        }
    }
}
