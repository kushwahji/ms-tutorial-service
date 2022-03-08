/**
 * 
 */
package com.santosh.javatutorials.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.santosh.javatutorials.entity.Image;

/**
 * @author santosh.kushwah
 *
 */
@Repository
public interface ImageRepositoty extends MongoRepository<Image, Long>{

}
