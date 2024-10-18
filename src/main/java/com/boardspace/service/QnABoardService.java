package com.boardspace.service;

import com.boardspace.dto.Pagination;
import com.boardspace.mapper.QnABoardMapper;
import com.boardspace.model.QnAPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnABoardService {
    private final QnABoardMapper qnABoardMapper;

    public void writePost(QnAPost post) {
        int result = qnABoardMapper.insertPost(post);
    }

    public void updateById(long id, QnAPost post) {
        qnABoardMapper.updateById(id, post);
    }

    public void deleteById(long id) {
        qnABoardMapper.deleteById(id);
    }

    public Optional<QnAPost> findById(long id) {
        return qnABoardMapper.findById(id);
    }

    public Pagination<QnAPost> getPosts(int page, int limit) {
        // 0-based 인덱스 방식으로 페이지 변환
        int start = (page - 1) * limit;
        long totalElements = qnABoardMapper.countPosts();
        List<QnAPost> posts = qnABoardMapper.findPostsByPage(start, limit);
        return new Pagination<>(posts, page - 1, limit, totalElements);
    }

    public Pagination<QnAPost> findPostsByNickName(int page, int size, String nickname) {
        // 0-based 인덱스 방식으로 페이지 변환
        int start = (page - 1) * size;
        long totalElements = qnABoardMapper.countPostsByNickname(nickname);
        List<QnAPost> posts = qnABoardMapper.findPostsByPageAndNickname(start, size, nickname);
        return new Pagination<>(posts,page - 1, size, totalElements);
    }
}