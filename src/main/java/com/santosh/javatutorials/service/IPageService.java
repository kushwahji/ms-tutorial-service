package com.santosh.javatutorials.service;

import com.santosh.javatutorials.request.ImageDto;
import com.santosh.javatutorials.response.MenuResponseDto;
import com.santosh.javatutorials.response.TopicResponseDto;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface IPageService {
    List<TopicResponseDto> allTopic(String name);

    List<MenuResponseDto> allMenu();

    List<TopicResponseDto> allSearch(String search);

	String addImages(MultipartFile file, ImageDto req);

	List<TopicResponseDto> allTopic();

	Map<String, List<TopicResponseDto>> sideBar(String name);
}
