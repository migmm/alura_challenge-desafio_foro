package com.alura.foro.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicDTO {
    private String title;
    private String message;
    private String author;
    private String course;
}