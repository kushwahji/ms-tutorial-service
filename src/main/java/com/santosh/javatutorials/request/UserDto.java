/**
 * 
 */
package com.santosh.javatutorials.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Data
@NoArgsConstructor
public class UserDto {
	private String username;
	private String password;
}
