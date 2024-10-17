package com.boardspace.service;

import com.boardspace.model.CommunityPost;
import com.boardspace.repository.CommunityBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityBoardService {
    private final CommunityBoardRepository commBoardRepository;
    private long id = 1;

    public void writePost(String nickname, CommunityPost post) {
        post.setId(id++);
        post.setNickname(nickname);
        post.setViewCount(0);
        post.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        post.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        commBoardRepository.save(post);
    }

    public void updateById(long id, CommunityPost post) {
        commBoardRepository.updateById(id, post);
    }

    public void deleteById(long id) {
        commBoardRepository.deleteById(id);
    }

    public Optional<CommunityPost> findById(long id) {
        return commBoardRepository.findById(id);
    }

    public Pagination<CommunityPost> getPosts(int page, int size) {
        // 0-based 인덱스 방식으로 페이지 변환
        int start = (page - 1) * size;
        long totalElements = commBoardRepository.count();
        List<CommunityPost> posts = commBoardRepository.findPostsByPage(start, size);
        return new Pagination<>(posts, page - 1, size, totalElements);
    }

    public Pagination<CommunityPost> findPostsByNickName(int page, int size, String nickname) {
        // 0-based 인덱스 방식으로 페이지 변환
        int start = (page - 1) * size;
        long totalElements = commBoardRepository.countByNickname(nickname);
        List<CommunityPost> posts = commBoardRepository.findPostsByPageAndNickname(start, size, nickname);
        return new Pagination<>(posts,page - 1, size, totalElements);
    }

    //public List<Board> findAllByOrderByIdDesc() {
    //    return boardRepository.findAllByOrderByIdDesc();
    //}
}