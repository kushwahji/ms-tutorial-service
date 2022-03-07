package com.santosh.javatutorials.request;

import com.santosh.javatutorials.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

	@NotBlank
    private String image;

    public ImageDto(Image topic) {
        this.image = topic.getImagePath();
    }
}
