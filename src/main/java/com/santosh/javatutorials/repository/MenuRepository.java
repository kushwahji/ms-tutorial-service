package com.santosh.javatutorials.repository;

import com.santosh.javatutorials.entity.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends MongoRepository<Menu,Long> {
	Menu findByNameAndStatusTrue(String name);

	Menu findTopByOrderByMenuIdDesc();

	void deleteByMenuId(long id);

	Menu findByMenuId(long id);

}
