/**
 * 
 */
package com.santosh.javatutorials.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Data
@Document(collection = "item")
@NoArgsConstructor
public class User {
	@Id
	private Long id;
	private String username;
	private String password;
}
