package com.alura.foro.service;

import com.alura.foro.dto.ReplyDTO;
import com.alura.foro.model.Reply;
import com.alura.foro.model.Topic;
import com.alura.foro.model.User;
import com.alura.foro.repository.ReplyRepository;
import com.alura.foro.repository.TopicRepository;
import com.alura.foro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    public Reply createReply(ReplyDTO replyDTO) {
        Topic topic = topicRepository.findById(replyDTO.getTopicId())
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        User user = userRepository.findById(replyDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Reply reply = new Reply();
        reply.setMessage(replyDTO.getMessage());
        reply.setTopic(topic);
        reply.setUser(user);
        reply.setCreatedAt(LocalDateTime.now());
        reply.setStatus("ACTIVO");

        return replyRepository.save(reply);
    }

    public List<Reply> getRepliesByTopic(Long topicId) {
        return replyRepository.findByTopicId(topicId);
    }

    public void deleteReply(Long id) {
        replyRepository.deleteById(id);
    }
}