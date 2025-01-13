package com.alura.foro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDTO {
    @NotBlank(message = "El mensaje no puede estar vacío")
    private String message;

    @NotNull(message = "El ID del tópico no puede ser nulo")
    private Long topicId;

    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Long userId;
}