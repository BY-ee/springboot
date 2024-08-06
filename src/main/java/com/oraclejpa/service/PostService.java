package com.oraclejpa.service;

import com.oraclejpa.model.Post;
import com.oraclejpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void save(Post post) {
        postRepository.save(post);
    }
}