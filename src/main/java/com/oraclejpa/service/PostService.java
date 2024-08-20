package com.oraclejpa.service;

import com.oraclejpa.model.Post;
import com.oraclejpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void writePost(String nickname, @ModelAttribute Post post) {
        post.setNickname(nickname);
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

//    public List<Post> findAllByOrderByIdDesc() {
//        return postRepository.findAllByOrderByIdDesc();
//    }

    public Page<Post> getPosts(int page, int size) {
        int start = (page - 1) * size + 1;
        int end = start + size - 1;
//        int end = page * size;
        long totalElements = postRepository.count(); // 전체 데이터 수
        List<Post> posts = postRepository.findAllPosts(start, end); // 수정된 쿼리 호출
        return new PageImpl<>(posts, PageRequest.of(page - 1, size), totalElements);
    }

    public Page<Post> findPostsByNickName(int page, int size, String nickname) {
        int start = (page - 1) * size + 1;
        int end = start + size - 1;
//        int end = page * size;
        long totalElements = postRepository.countByNickname(nickname);
        List<Post> posts = postRepository.findPostsByNickname(start, end, nickname);
        return new PageImpl<>(posts, PageRequest.of(page - 1, size), totalElements);
    }
}