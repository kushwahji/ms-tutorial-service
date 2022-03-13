/**
 * 
 */
package com.santosh.javatutorials.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.santosh.javatutorials.request.ImageDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 05-03-2022
 */
@Document(collection = "images")
@Data
@NoArgsConstructor
public class Image {

	@Id
	private String id;
	
	private Long imgId;
	
    private String imagePath;
    
    public Image(ImageDto req) {
		this.imagePath = req.getImage();
	}

 
}
