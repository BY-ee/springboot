package com.boardspace.service;

import com.boardspace.model.QnAPost;
import com.boardspace.repository.QnABoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnABoardService {
    private final QnABoardRepository qnABoardRepository;
    private long id = 1;

    public void writePost(String nickname, QnAPost post) {
        post.setId(id++);
        post.setNickname(nickname);
        post.setViewCount(0);
        post.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        post.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        qnABoardRepository.save(post);
    }

    public void updateById(long id, QnAPost post) {
        qnABoardRepository.updateById(id, post);
    }

    public void deleteById(long id) {
        qnABoardRepository.deleteById(id);
    }

    public Optional<QnAPost> findById(long id) {
        return qnABoardRepository.findById(id);
    }

    public Pagination<QnAPost> getPosts(int page, int size) {
        // 0-based 인덱스 방식으로 페이지 변환
        int start = (page - 1) * size;
        long totalElements = qnABoardRepository.count();
        List<QnAPost> posts = qnABoardRepository.findPostsByPage(start, size);
        return new Pagination<>(posts, page - 1, size, totalElements);
    }

    public Pagination<QnAPost> findPostsByNickName(int page, int size, String nickname) {
        // 0-based 인덱스 방식으로 페이지 변환
        int start = (page - 1) * size;
        long totalElements = qnABoardRepository.countByNickname(nickname);
        List<QnAPost> posts = qnABoardRepository.findPostsByPageAndNickname(start, size, nickname);
        return new Pagination<>(posts,page - 1, size, totalElements);
    }

    //public List<Board> findAllByOrderByIdDesc() {
    //    return boardRepository.findAllByOrderByIdDesc();
    //}
}