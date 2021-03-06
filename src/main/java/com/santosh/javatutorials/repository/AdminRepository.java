package com.santosh.javatutorials.repository;

import com.santosh.javatutorials.entity.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AdminRepository extends MongoRepository<Topic,Long> {
	
    List<Topic> findByNameContainingAndStatusTrue(String name);

    List<Topic> findByStatusTrueAndNameContainingOrQuestionContainingOrDescriptionContaining(String search,String search2,String search3);

	List<Topic> findByMenuId(Long id);

	Topic findTopByOrderByTopicIdDesc();

	Topic findByTopicId(long id);

	void deleteByTopicId(long id);

	Topic findByNameAndStatusTrue(String name);

}
