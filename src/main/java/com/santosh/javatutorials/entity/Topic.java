package com.santosh.javatutorials.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.santosh.javatutorials.request.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "topic")
public class Topic {

    @Id
    private Long topicId;

    private Long menuId;

    private String name;

    private String question;

    private String description;
    
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    private boolean status;

    public Topic(TopicDto request) {
        this.name = request.getName();
        this.question = request.getQuestion();
        this.description = request.getDescription();
        this.menuId = request.getMenuId();
        this.status=true;
        this.createdOn = LocalDateTime.now();
    }
}
