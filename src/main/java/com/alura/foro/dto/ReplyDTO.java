package com.alura.foro.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyDTO {

    @NotEmpty(message = "El mensaje no puede estar vacío")
    @Size(min = 10, max = 500, message = "El mensaje debe tener entre 10 y 500 caracteres")
    private String message;

    @NotEmpty(message = "El estado no puede estar vacío")
    @Size(min = 5, max = 20, message = "El estado debe tener entre 5 y 20 caracteres")
    private String status;

    @NotNull(message = "El ID del tópico no puede ser nulo")
    private Long topicId;

    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Long userId;

    private LocalDateTime createdAt;
    private String username;
}