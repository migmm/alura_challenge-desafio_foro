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
import java.util.Optional;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    public Reply createReply(ReplyDTO replyDTO) {
        Reply reply = new Reply();
        reply.setMessage(replyDTO.getMessage());
        reply.setStatus(replyDTO.getStatus());
        reply.setCreatedAt(LocalDateTime.now());

        Topic topic = topicRepository.findById(replyDTO.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));
        User user = userRepository.findById(replyDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        reply.setTopic(topic);
        reply.setUser(user);

        return replyRepository.save(reply);
    }

    public List<Reply> getAllReplies() {
        return replyRepository.findAll();
    }

    public Optional<Reply> getReplyById(Long id) {
        return replyRepository.findById(id);
    }

    public Reply updateReply(Long id, ReplyDTO replyDTO) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reply not found"));

        reply.setMessage(replyDTO.getMessage());
        reply.setStatus(replyDTO.getStatus());

        return replyRepository.save(reply);
    }

    public void deleteReply(Long id) {
        replyRepository.deleteById(id);
    }
}