package com.santosh.javatutorials.request;

import com.santosh.javatutorials.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

    @NotBlank(message = "name should not be blank or null")
    private String name;
    
    @NotBlank(message = "question should not be blank or null")
    private String question;
    
    @NotBlank(message = "description should not be blank or null")
    private String description;
    
    @Min(value = 1)
    private Long menuId;

    public TopicDto(Topic topic) {
        this.name=topic.getName();
        this.question = topic.getQuestion();
        this.description = topic.getDescription();
    }
}
