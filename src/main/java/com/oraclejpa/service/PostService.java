package com.oraclejpa.service;

import com.oraclejpa.model.Post;
import com.oraclejpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void savePost(@ModelAttribute Post post) {
        postRepository.save(post);
    }

    public void updateById(Long id, @ModelAttribute Post post) {
        postRepository.updateById(id, post.getTitle(), post.getContent());
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> findAllByOrderByIdDesc() {
        return postRepository.findAllByOrderByIdDesc();
    }

    public Page<Post> getPosts(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size));
    }



//    public void resetPostSequence() {
//        postRepository.dropPostSequence();
//        postRepository.createPostSequence();
//    }
}