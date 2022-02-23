package com.santosh.javatutorials.service;

import com.santosh.javatutorials.repository.AdminRepository;
import com.santosh.javatutorials.repository.MenuRepository;
import com.santosh.javatutorials.response.MenuResponseDto;
import com.santosh.javatutorials.response.TopicResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PageService implements IPageService {
    @Autowired
    AdminRepository repository;

    @Autowired
    MenuRepository menuRepository;

    @Override
    public List<TopicResponseDto> allTopic(Long menuId) {

        Long id = menuRepository.findById(menuId).get().getMenuId();
        return repository.findAll().stream().filter(f->f.getMenuId()==id).map(TopicResponseDto::new ).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponseDto> allMenu() {
        return menuRepository.findAll().stream()
                .distinct().map(MenuResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TopicResponseDto> allSearch(String search) {
        return repository.findByNameContainingAndDescriptionContaining(search,search);
    }
}
