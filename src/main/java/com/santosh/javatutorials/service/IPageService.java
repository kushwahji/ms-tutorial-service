package com.santosh.javatutorials.service;

import com.santosh.javatutorials.request.TopicDto;
import com.santosh.javatutorials.response.AdverstiseDtoResponse;
import com.santosh.javatutorials.response.MenuResponseDto;
import com.santosh.javatutorials.response.TopicResponseDto;
import java.util.List;

public interface IPageService {
    List<TopicResponseDto> allTopic(String name);

    List<MenuResponseDto> allMenu();

	List<TopicResponseDto> allTopic();

	MenuResponseDto getMenu(long id);

	TopicResponseDto getTopic(long id);

	List<TopicDto> topics();

	List<TopicDto> topics(String name);

	TopicDto topics(Long id);

	 List<MenuResponseDto> menus();

	List<String> allSide(String name);

	List<AdverstiseDtoResponse> getAds();

	AdverstiseDtoResponse getAds(long id);
}
