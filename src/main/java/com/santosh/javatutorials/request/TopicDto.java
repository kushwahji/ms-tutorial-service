package com.santosh.javatutorials.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {
    private String name;
}
