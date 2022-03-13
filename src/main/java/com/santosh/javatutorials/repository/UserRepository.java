/**
 * 
 */
package com.santosh.javatutorials.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.santosh.javatutorials.entity.User;


/**
 * @author santosh.kushwah
 *
 */
@Repository
public interface UserRepository extends MongoRepository<User, Long>{

	User findByUsernameAndPassword(String username, String password);

}
