package com.santosh.javatutorials.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.santosh.javatutorials.request.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topic")
public class Topic {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long topicId;

    @Column(name = "menu_id",nullable = false)
    private Long menuId;

    @Column(name = "topic_name",nullable = false)
    private String name;

    @Column(name = "question",nullable = false)
    private String question;

    @Column(name = "description",columnDefinition = "TEXT",nullable = false)
    private String description;

    @Column(name = "path",nullable = false)
    private String image;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedOn;
    private boolean status;

    public Topic(TopicDto request) {
        this.name = request.getName();
        this.question = request.getQuestion();
        this.description = request.getDescription();
        this.image = request.getImage();
        this.menuId = request.getMenuId();
        this.status=true;
    }


    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }

}
