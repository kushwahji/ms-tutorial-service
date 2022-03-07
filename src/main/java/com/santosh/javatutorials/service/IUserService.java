/**
 * 
 */
package com.santosh.javatutorials.service;

import com.santosh.javatutorials.request.UserDto;

/**
 * @author santosh.kushwah
 *
 */
public interface IUserService {

	boolean login(UserDto req);

}
