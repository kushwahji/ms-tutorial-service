/**
 * 
 */
package com.santosh.javatutorials.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.santosh.javatutorials.entity.Adverstise;

/**
 * @author santosh.kushwah
 *
 */
@Repository
public interface AdvertiseRepository extends MongoRepository<Adverstise, Long> {

	Adverstise findByAdsId(long id);

	void deleteByAdsId(long id);

	Adverstise findTopByOrderByAdsIdDesc();

}
