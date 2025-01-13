package com.alura.foro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TopicDTO {
    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    @NotBlank(message = "El mensaje no puede estar vacío")
    private String message;

    @NotBlank(message = "El autor no puede estar vacío")
    private String author;

    @NotBlank(message = "El curso no puede estar vacío")
    private String course;
}