package com.santosh.javatutorials.service;

import com.santosh.javatutorials.entity.Topic;
import com.santosh.javatutorials.repository.AdminRepository;
import com.santosh.javatutorials.repository.MenuRepository;
import com.santosh.javatutorials.response.MenuResponseDto;
import com.santosh.javatutorials.response.TopicResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PageService implements IPageService {

    @Autowired
    AdminRepository repository;

    @Autowired
    MenuRepository menuRepository;

    @Override
    public List<TopicResponseDto> allTopic(Long id) {
        if (id == null) {
            return repository.findAll().stream().map(TopicResponseDto::new).collect(Collectors.toList());
        }
        return repository.findAll().stream().filter(f -> f.getMenuId()==id || f.getTopicId()==id).map(TopicResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponseDto> allMenu() {
        return menuRepository.findAll().stream()
                .distinct().map(MenuResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TopicResponseDto> allSearch(String search) {
        return repository.findByNameContainingOrQuestionContainingOrDescriptionContaining(search, search,search);
    }

    @Override
    public List<TopicResponseDto> allTopics(Long id) {
         Optional<Topic> tp = repository.findById(id);
        if(tp.isPresent()){
            return repository.findAll().stream().filter(f -> f.getMenuId()==tp.get().getMenuId()).map(TopicResponseDto::new).collect(Collectors.toList());
        }
        return repository.findAll().stream().filter(f -> f.getMenuId()==id).map(TopicResponseDto::new).collect(Collectors.toList());
    }
}
