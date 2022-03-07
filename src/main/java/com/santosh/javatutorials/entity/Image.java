/**
 * 
 */
package com.santosh.javatutorials.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.santosh.javatutorials.request.ImageDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 05-03-2022
 */
@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "Image_Path",nullable = false)
    private String imagePath;
    
    public Image(ImageDto req) {
		this.imagePath = req.getImage();
	}

 
}
