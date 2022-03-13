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
@NoArgsConstructor
@Document(collection = "advertise")
public class Adverstise {
	@Id
	private String id;
	
	private Long adsId;
	
	private String link;
	
	private boolean status;
}
