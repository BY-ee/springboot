package com.boardspace.service;

import com.boardspace.model.Post;
import com.boardspace.repository.MemoryPostRepository;
import com.boardspace.repository.PostRepository;
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
    private final MemoryPostRepository postRepository;

    public Post writePost(String nickname, @ModelAttribute Post post) {
        post.setNickname(nickname);
        return postRepository.save(post);
    }

    public void updateById(long id, @ModelAttribute Post post) {
        postRepository.updateById(id, post.getTitle(), post.getContent());
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Page<Post> getPosts(int page, int size) {
        int start = page * size;
        int end = start + size;
        long totalElements = postRepository.count(); // 전체 데이터 수
        List<Post> posts = postRepository.findAllPosts(start + 1, end); // 수정된 쿼리 호출
        return new PageImpl<>(posts, PageRequest.of(page, size), totalElements);
    }

    public Page<Post> findPostsByNickName(int page, int size, String nickname) {
        int start = page * size;
        int end = start + size;
        long totalElements = postRepository.countByNickname(nickname);
        List<Post> posts = postRepository.findPostsByNickname(start + 1, end, nickname);
        return new PageImpl<>(posts, PageRequest.of(page, size), totalElements);
    }

    //public List<Post> findAllByOrderByIdDesc() {
    //    return postRepository.findAllByOrderByIdDesc();
    //}
}