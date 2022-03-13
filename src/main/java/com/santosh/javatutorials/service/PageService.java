package com.santosh.javatutorials.service;

import com.santosh.javatutorials.entity.Menu;
import com.santosh.javatutorials.entity.Topic;
import com.santosh.javatutorials.exception.MsApplicationException;
import com.santosh.javatutorials.repository.AdminRepository;
import com.santosh.javatutorials.repository.AdvertiseRepository;
import com.santosh.javatutorials.repository.ImageRepositoty;
import com.santosh.javatutorials.repository.MenuRepository;
import com.santosh.javatutorials.request.TopicDto;
import com.santosh.javatutorials.response.AdverstiseDtoResponse;
import com.santosh.javatutorials.response.MenuResponseDto;
import com.santosh.javatutorials.response.TopicResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PageService implements IPageService {

	@Autowired
	AdminRepository repository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	ImageRepositoty imageRepository;
	
	@Autowired
	AdvertiseRepository  advertiseRepositoy;

	@Override
	public List<MenuResponseDto> allMenu() {
		return menuRepository.findAll().stream().filter(Menu::isStatus).distinct().map(MenuResponseDto::new)
				.collect(Collectors.toList());
	}

	@Override
	public List<TopicResponseDto> allTopic(String name) {

		if (name != null && name.startsWith("menu")) {
			Menu menu = menuRepository.findByNameAndStatusTrue(name.replace("menu", ""));
			if (null != menu) {
				return repository.findByMenuId(menu.getMenuId()).stream().filter(Topic::isStatus).map(TopicResponseDto::new)
						.collect(Collectors.toList());
			}
		} else if (name != null && name.startsWith("search")) {
			String tilte = name.replace("s", "");
			return repository
					.findByStatusTrueAndNameContainingOrQuestionContainingOrDescriptionContaining(tilte, tilte, tilte)
					.stream().filter(Topic::isStatus).map(TopicResponseDto::new).collect(Collectors.toList());
		}
		return repository.findByNameContainingAndStatusTrue(name).stream().filter(Topic::isStatus)
				.map(TopicResponseDto::new).collect(Collectors.toList());

	}

	@Override
	public List<TopicResponseDto> allTopic() {
		return repository.findAll().stream().map(TopicResponseDto::new).collect(Collectors.toList());
	}

	@Override
	public MenuResponseDto getMenu(long id) {
		return new MenuResponseDto(menuRepository.findByMenuId(id));
	}

	@Override
	public TopicResponseDto getTopic(long id) {
		return new TopicResponseDto(repository.findByTopicId(id));
	}

	@Override
	public List<TopicDto> topics() {
		return Optional.ofNullable(repository.findAll().stream().map(TopicDto::new).collect(Collectors.toList()))
				.orElseThrow(() -> new MsApplicationException("record not found", "record not found"));
	}

	@Override
	public List<TopicDto> topics(String name) {
		return Optional
				.ofNullable(repository.findByNameContainingAndStatusTrue(name).stream().map(TopicDto::new)
						.collect(Collectors.toList()))
				.orElseThrow(() -> new MsApplicationException("record not found", "record not found for " + name));
	}

	@Override
	public TopicDto topics(Long id) {
		return new TopicDto(Optional.ofNullable(repository.findByTopicId(id))
				.orElseThrow(() -> new MsApplicationException("record not found", "record not found for " + id)));
	}

	@Override
	public List<MenuResponseDto> menus() {
		return menuRepository.findAll().stream().distinct().map(MenuResponseDto::new).collect(Collectors.toList());
	}

	@Override
	public List<String> allSide(String name) {
		List<String> side = new ArrayList<>();
		if (name.startsWith("menu")) {
			name = name.replace("menu", "");
			Long id = menuRepository.findByNameAndStatusTrue(name).getMenuId();
			prapareSide(id).stream().forEach(side::add);
		}else {
			repository.findByNameContainingAndStatusTrue(name).stream().map(m->prapareSide(m.getMenuId())).forEach(f1->side.addAll(f1));
		}
		return side;
	}

	private List<String> prapareSide(Long menuId) {
		return repository.findAll().stream().filter(f -> f.isStatus() && Objects.equals(f.getMenuId(), menuId)).map(Topic::getName)
				.distinct().collect(Collectors.toList());
	}

	@Override
	public List<AdverstiseDtoResponse> getAds() {
		return advertiseRepositoy.findAll().stream().map(AdverstiseDtoResponse::new).collect(Collectors.toList());
	}

	@Override
	public AdverstiseDtoResponse getAds(long id) {
		return new AdverstiseDtoResponse(advertiseRepositoy.findByAdsId(id));
	}

}
