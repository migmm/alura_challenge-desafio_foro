package com.alura.foro.controller;

import com.alura.foro.dto.TopicDTO;
import com.alura.foro.model.Topic;
import com.alura.foro.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    // Crear un nuevo tópico
    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody TopicDTO topicDTO) {
        Topic createdTopic = topicService.createTopic(topicDTO);
        return ResponseEntity.ok(createdTopic);
    }

    // Obtener todos los tópicos
    @GetMapping
    public ResponseEntity<Page<Topic>> getAllTopics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy) {
        Page<Topic> topics = topicService.getAllTopics(page, size, sortBy);
        return ResponseEntity.ok(topics);
    }

    // Obtener un tópico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        return topicService.getTopicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar un tópico
    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody TopicDTO topicDTO) {
        Topic updatedTopic = topicService.updateTopic(id, topicDTO);
        return ResponseEntity.ok(updatedTopic);
    }

    // Eliminar un tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}