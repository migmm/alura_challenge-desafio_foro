package com.alura.foro.controller;

import com.alura.foro.dto.ReplyDTO;
import com.alura.foro.model.Reply;
import com.alura.foro.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/respuestas")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping
    public ResponseEntity<Reply> createReply(@Valid @RequestBody ReplyDTO replyDTO) {
        return ResponseEntity.ok(replyService.createReply(replyDTO));
    }

    @GetMapping("/topico/{topicId}")
    public ResponseEntity<List<Reply>> getRepliesByTopic(@PathVariable Long topicId) {
        return ResponseEntity.ok(replyService.getRepliesByTopic(topicId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long id) {
        replyService.deleteReply(id);
        return ResponseEntity.noContent().build();
    }
}