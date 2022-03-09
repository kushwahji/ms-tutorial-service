package com.santosh.javatutorials.service;

import com.santosh.javatutorials.entity.Menu;
import com.santosh.javatutorials.entity.Topic;
import com.santosh.javatutorials.exception.MsApplicationException;
import com.santosh.javatutorials.repository.AdminRepository;
import com.santosh.javatutorials.repository.MenuRepository;
import com.santosh.javatutorials.request.MenuDto;
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

	@Autowired
	MenuRepository menuRepository;

	@Override
	public void addTopic(TopicDto request) {
		if (adminRepository.findAll().isEmpty()) {
			adminRepository.save(new Topic(request, 1L));
		} else {
			adminRepository.findAll().stream().map(m -> adminRepository.save(new Topic(request, m.getMenuId() + 1)))
					.collect(Collectors.toList());
		}
	}

	@Override
	public List<TopicDto> topics() {
		return Optional.ofNullable(adminRepository.findAll().stream().map(TopicDto::new).collect(Collectors.toList()))
				.orElseThrow(() -> new MsApplicationException("record not found", "record not found"));
	}

	@Override
	public List<TopicDto> topics(String name) {
		return Optional
				.ofNullable(adminRepository.findByNameContaining(name).stream().map(TopicDto::new)
						.collect(Collectors.toList()))
				.orElseThrow(() -> new MsApplicationException("record not found", "record not found for " + name));
	}

	@Override
	public TopicDto topics(Long id) {
		return new TopicDto(Optional.ofNullable(adminRepository.findById(id).get())
				.orElseThrow(() -> new MsApplicationException("record not found", "record not found for " + id)));
	}

	@Override
	public void updateTopic(Long id, TopicDto request) {

		if (adminRepository.existsById(id)) {
			TopicDto topic = topics(id);
			Optional.ofNullable(topic).orElseThrow(
					() -> new MsApplicationException("record not found", "record not found for this id" + id));
			topic.setName(request.getName());
			adminRepository.save(new Topic(topic, autoTopicId()));

		}
	}

	@Override
	public void addMenu(MenuDto request) {
		menuRepository.save(new Menu(request, autoMenuId()));
	}

	public Long autoMenuId() {
		Long id = 1L;
		if (!menuRepository.findAll().isEmpty()) {
			Menu menu = menuRepository.findTopByOrderByMenuIdDesc();
			if (menu != null) {
				return menu.getMenuId() + 1;
			}
		}
		return id;
	}

	public Long autoTopicId() {
		Long id = 1L;
		if (!adminRepository.findAll().isEmpty()) {
			Topic topic = adminRepository.findTopByOrderByTopicIdDesc();
			if (topic != null) {
				return topic.getMenuId() + 1;
			}
		}
		return id;
	}
}
