package com.alura.foro.controller;

import com.alura.foro.dto.ReplyDTO;
import com.alura.foro.model.Reply;
import com.alura.foro.service.ReplyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    // Crear una nueva respuesta
    @PostMapping
    public ResponseEntity<Reply> createReply(@Valid @RequestBody ReplyDTO replyDTO) {
        Reply createdReply = replyService.createReply(replyDTO);
        return new ResponseEntity<>(createdReply, HttpStatus.CREATED);
    }

    // Obtener todas las respuestas
    @GetMapping
    public ResponseEntity<List<Reply>> getAllReplies() {
        List<Reply> replies = replyService.getAllReplies();
        return ResponseEntity.ok(replies);
    }

    // Obtener una respuesta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reply> getReplyById(@PathVariable Long id) {
        Optional<Reply> reply = replyService.getReplyById(id);
        return reply.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar una respuesta
    @PutMapping("/{id}")
    public ResponseEntity<Reply> updateReply(@PathVariable Long id, @Valid @RequestBody ReplyDTO replyDTO) {
        Reply updatedReply = replyService.updateReply(id, replyDTO);
        return ResponseEntity.ok(updatedReply);
    }

    // Eliminar una respuesta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long id) {
        replyService.deleteReply(id);
        return ResponseEntity.noContent().build();
    }
}