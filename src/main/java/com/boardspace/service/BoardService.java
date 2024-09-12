package com.boardspace.service;

import com.boardspace.model.CommunityBoard;
import com.boardspace.repository.MemoryBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final MemoryBoardRepository postRepository;

    public void writePost(String nickname, CommunityBoard post) {
        post.setNickname(nickname);
        postRepository.save(post);
    }

    public void updateById(long id, CommunityBoard post) {
        postRepository.updateById(id, post);
    }

    public void deleteById(long id) {
        postRepository.deleteById(id);
    }

    public Optional<CommunityBoard> findById(long id) {
        return postRepository.findById(id);
    }

    public Page<CommunityBoard> getPosts(int page, int size) {
        // 0-based 인덱스 방식으로 페이지 변환
        int start = (page - 1) * size;
        long totalElements = postRepository.count();
        List<CommunityBoard> posts = postRepository.findPostsByPage(start, size);
        return new PageImpl<>(posts, PageRequest.of(page - 1, size), totalElements);
    }

    public Page<CommunityBoard> findPostsByNickName(int page, int size, String nickname) {
        // 0-based 인덱스 방식으로 페이지 변환
        int start = (page - 1) * size;
        long totalElements = postRepository.countByNickname(nickname);
        List<CommunityBoard> posts = postRepository.findPostsByPageAndNickname(start, size, nickname);
        return new PageImpl<>(posts, PageRequest.of(page - 1, size), totalElements);
    }

    //public List<Post> findAllByOrderByIdDesc() {
    //    return postRepository.findAllByOrderByIdDesc();
    //}
}