package com.santosh.javatutorials.service;

import com.santosh.javatutorials.constant.Constants;
import com.santosh.javatutorials.entity.Adverstise;
import com.santosh.javatutorials.entity.Image;
import com.santosh.javatutorials.entity.Menu;
import com.santosh.javatutorials.entity.Topic;
import com.santosh.javatutorials.repository.AdminRepository;
import com.santosh.javatutorials.repository.AdvertiseRepository;
import com.santosh.javatutorials.repository.ImageRepositoty;
import com.santosh.javatutorials.repository.MenuRepository;
import com.santosh.javatutorials.request.ImageDto;
import com.santosh.javatutorials.request.MenuDto;
import com.santosh.javatutorials.request.TopicDto;
import com.santosh.javatutorials.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class AdminService implements IAdminService {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	ImageRepositoty imageRepository;
	
	
	@Autowired
	AdvertiseRepository advertiseRepository;

	@Override
	public void addTopic(TopicDto topic) {
		if (topic != null && topic.getTopicId() != null) {
			Topic tp = adminRepository.findByTopicId(topic.getTopicId());
			tp.setDescription(topic.getDescription());
			tp.setName(topic.getName());
			tp.setQuestion(topic.getQuestion());
			adminRepository.save(tp);
		} else {
			adminRepository.save(new Topic(topic, autoTopicId()));
		}
	}

	@Override
	public void addMenu(MenuDto menu) {
		if (menu != null && menu.getMenuId() != null) {
			Menu mn = menuRepository.findByMenuId(menu.getMenuId());
			mn.setName(menu.getName());
			menuRepository.save(mn);
		} else {
			menuRepository.save(new Menu(menu, autoMenuId()));
		}
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
				return topic.getTopicId() + 1;
			}
		}
		return id;
	}
	
	public Long autoAdsId() {
		Long id = 1L;
		if (!advertiseRepository.findAll().isEmpty()) {
			Adverstise adsvertise = advertiseRepository.findTopByOrderByAdsIdDesc();
			if (adsvertise != null) {
				return adsvertise.getAdsId() + 1;
			}
		}
		return id;
	}

	@Override
	public void deleteMenu(long id) {
		menuRepository.deleteByMenuId(id);
	}

	@Override
	public void deleteTopic(long id) {
		adminRepository.deleteByTopicId(id);
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
	public void updateStatus(int menuId, boolean status) {
		if (menuId > 0) {
			if (status) {
				status = false;
			} else {
				status = true;
			}
			Menu mn = menuRepository.findByMenuId(menuId);
			mn.setStatus(status);
			menuRepository.save(mn);
		}
	}

	@Override
	public void updateStatusTopic(int topicId, boolean status) {
		if (topicId > 0) {
			if (status) {
				status = false;
			} else {
				status = true;
			}
			Topic tp = adminRepository.findByTopicId(topicId);
			tp.setStatus(status);
			adminRepository.save(tp);
		}
	}

	@Override
	public void deleteAdvertise(long id) {
		advertiseRepository.deleteByAdsId(id);
	}

	@Override
	public void addAdvertise(Adverstise req) {
		if (req != null && null!= req.getAdsId() && req.getAdsId()>0) {
			Adverstise ad = advertiseRepository.findByAdsId(req.getAdsId());
			ad.setLink(req.getLink());
			advertiseRepository.save(ad);
		} else {
			req.setAdsId(autoAdsId());
			req.setStatus(true);
			advertiseRepository.save(req);
		}
	}
	
	@Override
	public void updateStatusAdvertise(int adsId, boolean status) {
		if (adsId > 0) {
			if (status) {
				status = false;
			} else {
				status = true;
			}
			Adverstise ad = advertiseRepository.findByAdsId(adsId);
			ad.setStatus(status);
			advertiseRepository.save(ad);
		}
	}

	
	

}
