package com.alura.foro.service;

import com.alura.foro.dto.ReplyDTO;
import com.alura.foro.dto.TopicDTO;
import com.alura.foro.model.Topic;
import com.alura.foro.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    // Crear un nuevo tópico
    public Topic createTopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setTitle(topicDTO.getTitle());
        topic.setMessage(topicDTO.getMessage());
        topic.setAuthor(topicDTO.getAuthor());
        topic.setCourse(topicDTO.getCourse());
        topic.setCreatedAt(LocalDateTime.now());
        topic.setStatus("ACTIVE");
        return topicRepository.save(topic);
    }

    // Obtener todos los tópicos
    public Page<Topic> getAllTopics(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return topicRepository.findAll(pageable);
    }

    public TopicDTO getTopicWithRepliesById(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new RuntimeException("Topic not found"));
        TopicDTO dto = new TopicDTO(); // Usa TopicDTO
        dto.setId(topic.getId());
        dto.setTitle(topic.getTitle());
        dto.setMessage(topic.getMessage());
        dto.setCreatedAt(topic.getCreatedAt());
        dto.setStatus(topic.getStatus());
        dto.setAuthor(topic.getAuthor());
        dto.setCourse(topic.getCourse());

        // Mapea las respuestas a ReplyDTO
        List<ReplyDTO> replyDTOs = topic.getReplies().stream()
                .map(reply -> {
                    ReplyDTO replyDTO = new ReplyDTO();
                    replyDTO.setMessage(reply.getMessage());
                    replyDTO.setCreatedAt(reply.getCreatedAt());
                    replyDTO.setUsername(reply.getUser().getUsername()); // Asigna el username
                    replyDTO.setStatus(reply.getStatus());
                    replyDTO.setTopicId(reply.getTopic().getId());
                    replyDTO.setUserId(reply.getUser().getId());
                    return replyDTO;
                })
                .collect(Collectors.toList());

        dto.setReplies(replyDTOs); // Asigna las respuestas al DTO
        return dto;
    }

    // Obtener un tópico por ID
    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }

    // Actualizar un tópico
    public Topic updateTopic(Long id, TopicDTO topicDTO) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new RuntimeException("Topic not found"));
        topic.setTitle(topicDTO.getTitle());
        topic.setMessage(topicDTO.getMessage());
        topic.setAuthor(topicDTO.getAuthor());
        topic.setCourse(topicDTO.getCourse());
        return topicRepository.save(topic);
    }

    // Eliminar un tópico
    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }
}