package com.oraclejpa.service;

import com.oraclejpa.model.Post;
import com.oraclejpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void save(@ModelAttribute Post post) {
        postRepository.save(post);
    }

    public void deleteAll() {
        postRepository.deleteAll();
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }
}