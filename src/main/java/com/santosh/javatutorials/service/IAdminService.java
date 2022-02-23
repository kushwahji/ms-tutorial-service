package com.santosh.javatutorials.service;

import com.santosh.javatutorials.request.MenuDto;
import com.santosh.javatutorials.request.TopicDto;

import java.util.List;

public interface IAdminService {
    void addTopic(TopicDto request);

    List<TopicDto> topics();

    List<TopicDto> topics(String name);

    TopicDto topics(Long id);

    void updateTopic(Long id, TopicDto request);

    void addMenu(MenuDto request);
}
