package com.boardspace.service;

import com.boardspace.constants.PaginationConstant;
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

    public void updatePostById(QnAPost newPost) {
        qnABoardMapper.updatePostById(newPost);
    }

    public void deletePostById(long id) {
        qnABoardMapper.deletePostById(id);
    }

    public Optional<QnAPost> findPostById(long id) {
        return qnABoardMapper.findPostById(id);
    }

    public List<QnAPost> findPosts(int page, Integer limit) {
        // 0-based 인덱스 방식으로 페이지 변환
        limit = validateLimit(limit);
        int offset = (page - 1) * limit;

        long countPosts = qnABoardMapper.countPosts();

        if(countPosts < limit + offset) {
            limit = (int) countPosts - offset;
        }

        return qnABoardMapper.findPosts(limit, offset);
    }

    public List<QnAPost> findPostsByUserId(int page, Integer limit, long userId) {
        // 0-based 인덱스 방식으로 페이지 변환
        limit = validateLimit(limit);
        int offset = (page - 1) * limit;

        long countPostsById = qnABoardMapper.countPostsById(userId);

        if(countPostsById < limit + offset) {
            limit = (int) countPostsById - offset;
        }

        return qnABoardMapper.findPostsById(limit, offset, userId);
    }

    private int validateLimit(Integer limit) {
        if(limit == null || limit < 5 || limit > 50) {
            limit = PaginationConstant.PAGE_LIMIT;
        }

        return limit;
    }
}