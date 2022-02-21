package com.santosh.javatutorials.repository;

import com.santosh.javatutorials.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Topic,Long> {
    List<Topic> findByNameContaining(String name);
}
