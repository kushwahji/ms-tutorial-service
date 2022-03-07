package com.santosh.javatutorials.repository;

import com.santosh.javatutorials.entity.Topic;
import com.santosh.javatutorials.response.TopicResponseDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Topic,Long> {
	
    List<Topic> findByNameContaining(String name);

    List<Topic> findByNameContainingOrQuestionContainingOrDescriptionContaining(String search,String search2,String search3);

	List<Topic> findByMenuId(Long id);
}
