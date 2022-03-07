/**
 * 
 */
package com.santosh.javatutorials.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santosh.javatutorials.entity.User;
import com.santosh.javatutorials.repository.UserRepository;
import com.santosh.javatutorials.request.UserDto;

/**
 * @author santosh.kushwah
 *
 */
@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	@Override
	public boolean login(UserDto req) {
		User user = userRepository.findByUsernameAndPassword(req.getUsername(),req.getPassword());
		if(user!=null) {
			return true;
		}
		return false;
	}

}
