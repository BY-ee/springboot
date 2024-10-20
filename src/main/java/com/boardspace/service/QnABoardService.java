package com.boardspace.service;

import com.boardspace.constants.PaginationConstant;
import com.boardspace.dto.Pagination;
import com.boardspace.dto.PostDTO;
import com.boardspace.mapper.PostMapper;
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

    // 글 작성
    public void writePost(QnAPost post) {
        int result = qnABoardMapper.insertPost(post);
    }

    // 글 수정
    public void updatePostById(QnAPost newPost) {
        qnABoardMapper.updatePostById(newPost);
    }

    // 글 삭제
    public void deletePostById(long id) {
        qnABoardMapper.deletePostById(id);
    }

    // 게시글 조회
    public Optional<PostDTO> findPostById(long id) {
        Optional<QnAPost> qnAPost = qnABoardMapper.findPostById(id);

        PostDTO postDTO = PostMapper.INSTANCE.ToPostDTO(qnAPost.orElseThrow());
        qnAPost.ifPresent(post -> {
            postDTO.setCreatedAt(post.getCreatedAt().toInstant().toEpochMilli());
            postDTO.setUpdatedAt(post.getUpdatedAt().toInstant().toEpochMilli());
        });
        return Optional.of(postDTO);
    }

    // 게시글 페이징 처리
    public Pagination<QnAPost> findPosts(int page, Integer limit) {
        // 0-based 인덱스 방식으로 페이지 변환
        limit = validateLimit(limit);
        int offset = (page - 1) * limit;

        long totalElements = qnABoardMapper.countPosts();
        limit = Math.min(limit, (int) totalElements - offset);

        List<QnAPost> posts = qnABoardMapper.findPosts(limit, offset);
        return new Pagination<>(posts, limit, offset, totalElements);
    }

    // 특정 유저의 게시글 페이징 처리
    public Pagination<QnAPost> findPostsByUserId(int page, Integer limit, long userId) {
        // 0-based 인덱스 방식으로 페이지 변환
        limit = validateLimit(limit);
        int offset = (page - 1) * limit;

        long totalElements = qnABoardMapper.countPostsByUserId(userId);
        limit = Math.min(limit, (int) totalElements - offset);


        List<QnAPost> posts = qnABoardMapper.findPostsByUserId(limit, offset, userId);
        return new Pagination<>(posts, limit, offset, totalElements);
    }

    // 조회수 증가
    public void increaseViewCount(long id) {
        qnABoardMapper.increaseViewCount(id);
    }

    // limit 값 유효성 검증
    private int validateLimit(Integer limit) {
        if(limit == null || limit < 5 || limit > 50) {
            limit = PaginationConstant.PAGE_LIMIT;
        }

        return limit;
    }
}