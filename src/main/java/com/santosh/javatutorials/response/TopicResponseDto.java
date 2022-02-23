package com.santosh.javatutorials.response;

import com.santosh.javatutorials.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponseDto {
    private Long topicId;
    private String name;
    private String question;
    private String description;

    public TopicResponseDto(Topic topic) {
        this.topicId = topic.getTopicId();
        this.name=topic.getName();
        this.question = topic.getQuestion();
        this.description = topic.getDescription();
    }
}
