/**
 * 
 */
package com.santosh.javatutorials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.javatutorials.entity.User;


/**
 * @author santosh.kushwah
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsernameAndPassword(String username, String password);

}
