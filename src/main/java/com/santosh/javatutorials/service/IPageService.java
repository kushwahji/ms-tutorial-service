package com.santosh.javatutorials.service;

import com.santosh.javatutorials.response.MenuResponseDto;
import com.santosh.javatutorials.response.TopicResponseDto;

import java.util.List;

public interface IPageService {
    List<TopicResponseDto> allTopic(Long id);

    List<MenuResponseDto> allMenu();

    List<TopicResponseDto> allSearch(String search);

    List<TopicResponseDto> allTopics(Long id);
}
