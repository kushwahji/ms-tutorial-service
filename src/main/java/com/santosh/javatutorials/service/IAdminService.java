package com.santosh.javatutorials.service;

import com.santosh.javatutorials.entity.Adverstise;
import com.santosh.javatutorials.request.ImageDto;
import com.santosh.javatutorials.request.MenuDto;
import com.santosh.javatutorials.request.TopicDto;


import org.springframework.web.multipart.MultipartFile;

public interface IAdminService {
    void addTopic(TopicDto request);

    void addMenu(MenuDto request);

	void deleteMenu(long id);

	void deleteTopic(long id);

	String addImages(MultipartFile file, ImageDto req);

	void updateStatus(int parseInt, boolean status);

	void updateStatusTopic(int topicId, boolean status);

	void deleteAdvertise(long id);

	void addAdvertise(Adverstise req);

	void updateStatusAdvertise(int adsId, boolean status);

}
