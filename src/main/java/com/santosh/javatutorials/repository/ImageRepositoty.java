/**
 * 
 */
package com.santosh.javatutorials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.javatutorials.entity.Image;

/**
 * @author santosh.kushwah
 *
 */
@Repository
public interface ImageRepositoty extends JpaRepository<Image, Long>{

}
