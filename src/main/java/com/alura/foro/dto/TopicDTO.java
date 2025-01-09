package com.alura.foro.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TopicDTO {

    @NotEmpty(message = "El título no puede estar vacío")
    @Size(min = 5, max = 100, message = "El título debe tener entre 5 y 100 caracteres")
    private String title;

    @NotEmpty(message = "El mensaje no puede estar vacío")
    @Size(min = 10, max = 500, message = "El mensaje debe tener entre 10 y 500 caracteres")
    private String message;

    @NotEmpty(message = "El autor no puede estar vacío")
    @Size(min = 3, max = 50, message = "El autor debe tener entre 3 y 50 caracteres")
    private String author;

    @NotEmpty(message = "El curso no puede estar vacío")
    @Size(min = 3, max = 50, message = "El curso debe tener entre 3 y 50 caracteres")
    private String course;

    private Long id;
    private LocalDateTime createdAt;
    private String status;
    private List<ReplyDTO> replies;
}