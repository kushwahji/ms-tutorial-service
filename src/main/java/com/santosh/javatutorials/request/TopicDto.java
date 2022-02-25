package com.santosh.javatutorials.request;

import com.santosh.javatutorials.entity.Menu;
import com.santosh.javatutorials.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

    @NotBlank(message = "name should not be blank or null")
    @NotNull(message = "name should not be blank or null")
    private String name;
    private String question;
    private String description;
    private String image;
    private Long menuId;

    public TopicDto(Topic topic) {
        this.name=topic.getName();
        this.question = topic.getQuestion();
        this.image = topic.getImage();
        this.description = topic.getDescription();
    }
}
