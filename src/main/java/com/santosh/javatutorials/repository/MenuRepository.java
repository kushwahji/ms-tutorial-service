package com.santosh.javatutorials.repository;

import com.santosh.javatutorials.entity.Menu;
import com.santosh.javatutorials.entity.Topic;
import com.santosh.javatutorials.request.MenuDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends MongoRepository<Menu,Long> {
	Menu findByName(String name);
}
