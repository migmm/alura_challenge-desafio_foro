package com.alura.foro.service;

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
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Topic createTopic(TopicDTO topicDTO) {
        if (topicRepository.findByTitleAndMessage(topicDTO.getTitle(), topicDTO.getMessage()).isPresent()) {
            throw new RuntimeException("El tópico ya existe");
        }

        Topic topic = new Topic();
        topic.setTitle(topicDTO.getTitle());
        topic.setMessage(topicDTO.getMessage());
        topic.setAuthor(topicDTO.getAuthor());
        topic.setCourse(topicDTO.getCourse());
        topic.setCreatedAt(LocalDateTime.now());
        topic.setStatus("ACTIVO");

        return topicRepository.save(topic);
    }

    public Page<Topic> getAllTopics(int page, int size, String[] sort) {
        Sort.Direction direction = sort[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        return topicRepository.findAll(pageable);
    }

    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }

    public Topic updateTopic(Long id, Topic topicDetails) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new RuntimeException("Topic not found"));
        topic.setTitle(topicDetails.getTitle());
        topic.setMessage(topicDetails.getMessage());
        topic.setStatus(topicDetails.getStatus());
        topic.setAuthor(topicDetails.getAuthor());
        topic.setCourse(topicDetails.getCourse());
        return topicRepository.save(topic);
    }

    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }
}