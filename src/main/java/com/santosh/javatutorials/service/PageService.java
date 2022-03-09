package com.santosh.javatutorials.service;

import com.santosh.javatutorials.constant.Constants;
import com.santosh.javatutorials.entity.Image;
import com.santosh.javatutorials.entity.Menu;
import com.santosh.javatutorials.repository.AdminRepository;
import com.santosh.javatutorials.repository.ImageRepositoty;
import com.santosh.javatutorials.repository.MenuRepository;
import com.santosh.javatutorials.request.ImageDto;
import com.santosh.javatutorials.response.MenuResponseDto;
import com.santosh.javatutorials.response.TopicResponseDto;
import com.santosh.javatutorials.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PageService implements IPageService {

	@Autowired
	AdminRepository repository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	ImageRepositoty imageRepository;

	@Override
	public List<MenuResponseDto> allMenu() {
		return menuRepository.findAll().filter(f ->f.isStatus()).stream().distinct().map(MenuResponseDto::new).collect(Collectors.toList());
	}

	public List<TopicResponseDto> allSearch(String search) {
		return repository.findByNameContainingOrQuestionContainingOrDescriptionContaining(search, search, search)
				.stream().map(TopicResponseDto::new).collect(Collectors.toList());
	}

	@Override
	public String addImages(MultipartFile file, ImageDto req) {
		String rootPath = System.getProperty("user.dir");
		if (file.isEmpty()) {
			return "Please select a file to upload.";
		}
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		req.setImage("/img/" + fileName);
		try {
			FileUploadUtil.saveFile(rootPath + Constants.UPLOAD_DIR, fileName, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageRepository.save(new Image(req));
		return "Image added successfully";
	}

	@Override
	public List<TopicResponseDto> allTopic(String name) {

		if (name != null && name.startsWith("menu")) {
			Menu menu = menuRepository.findByName(name.replace("menu", ""));
			if (null != menu) {
				return repository.findByMenuId(menu.getMenuId()).stream().map(TopicResponseDto::new)
						.collect(Collectors.toList());
			}
		} else if (name != null && name.startsWith("s")) {
			String tilte = name.replace("s", "");
			return repository.findByNameContainingOrQuestionContainingOrDescriptionContaining(tilte, tilte, tilte)
					.stream().map(TopicResponseDto::new).collect(Collectors.toList());
		}
		return repository.findByNameContaining(name).stream().map(TopicResponseDto::new).collect(Collectors.toList());

	}

	@Override
	public List<TopicResponseDto> allTopic() {
		return repository.findAll().stream().map(TopicResponseDto::new).collect(Collectors.toList());
	}

	@Override
	public Map<String, List<TopicResponseDto>> sideBar(String name) {
		if (name != null && !name.startsWith("menu")) {
			Long id = repository.findByNameContaining(name).get(0).getMenuId();
			return repository.findAll().stream().filter(f -> f.getMenuId() == id).map(TopicResponseDto::new)
					.collect(Collectors.groupingBy(TopicResponseDto::getName));
		} 

		else if(name != null){
			Menu menu = menuRepository.findByName(name.replace("menu", ""));
			if (null != menu) {
				return repository.findAll().stream().filter(f -> f.getMenuId() == menu.getMenuId())
						.map(TopicResponseDto::new).collect(Collectors.groupingBy(TopicResponseDto::getName));
			}
			
		}
		
		return null;
	}

}
