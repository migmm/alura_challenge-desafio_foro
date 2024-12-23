package com.alura.foro.dto;

import lombok.Data;


@Data
public class TopicDTO {
    private String title;
    private String message;
    private String author;
    private String course;
}