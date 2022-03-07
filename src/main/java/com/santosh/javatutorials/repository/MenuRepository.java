package com.santosh.javatutorials.repository;

import com.santosh.javatutorials.entity.Menu;
import com.santosh.javatutorials.entity.Topic;
import com.santosh.javatutorials.request.MenuDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
	Menu findByName(String name);
}
