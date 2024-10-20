package com.boardspace.service;

import com.boardspace.constants.PaginationConstant;
import com.boardspace.dto.Pagination;
import com.boardspace.mapper.CommunityBoardMapper;
import com.boardspace.model.CommunityPost;
import com.boardspace.model.QnAPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityBoardService {
    private final CommunityBoardMapper commBoardMapper;

    public void writePost(CommunityPost post) {
        int result = commBoardMapper.insertPost(post);
    }

    public void updatePostById(CommunityPost newPost) {
        commBoardMapper.updatePostById(newPost);
    }

    public void deletePostById(long id) {
        commBoardMapper.deletePostById(id);
    }

    public Optional<CommunityPost> findPostById(long id) {
        return commBoardMapper.findPostById(id);
    }

    public Pagination<CommunityPost> findPosts(int page, Integer limit) {
        // 0-based 인덱스 방식으로 페이지 변환
        limit = validateLimit(limit);
        int offset = (page - 1) * limit;

        long totalElements = commBoardMapper.countPosts();
        limit = Math.min(limit, (int) totalElements - offset);

        List<CommunityPost> posts = commBoardMapper.findPosts(limit, offset);
        return new Pagination<>(posts, limit, offset, totalElements);
    }

    public Pagination<CommunityPost> findPostsByUserId(int page, Integer limit, long userId) {
        // 0-based 인덱스 방식으로 페이지 변환
        limit = validateLimit(limit);
        int offset = (page - 1) * limit;

        long totalElements = commBoardMapper.countPostsByUserId(userId);
        limit = Math.min(limit, (int) totalElements - offset);


        List<CommunityPost> posts = commBoardMapper.findPostsByUserId(limit, offset, userId);
        return new Pagination<>(posts, limit, offset, totalElements);
    }

    private int validateLimit(Integer limit) {
        if(limit == null || limit < 5 || limit > 50) {
            limit = PaginationConstant.PAGE_LIMIT;
        }

        return limit;
    }
}