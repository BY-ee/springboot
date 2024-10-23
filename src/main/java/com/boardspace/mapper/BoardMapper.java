package com.boardspace.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper<T> {
    // 게시글 작성
    int insertPost(T Post);

    // 게시글 수정
    void updatePostById(T newPost);

    // 게시글 삭제
    void deletePostById(long id);

    // 게시글 조회
    Optional<T> findPostById(long id);

    // 페이징 처리된 게시글 조회
    List<T> findPosts(@Param("limit") int limit,
                      @Param("offset") int offset);

    // 특정 유저의 게시글을 페이징 처리 후 조회
    List<T> findPostsByUserId(@Param("limit") int limit,
                              @Param("offset") int offset,
                              @Param("userId") long userId);

    // 게시글 총 개수
    long countPosts();

    // 특정 유저가 작성한 게시글 총 개수
    long countPostsByUserId(long userId);

    // 조회수 증가
    void increaseViewCount(long id);
}