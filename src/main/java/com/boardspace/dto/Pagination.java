package com.boardspace.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class Pagination<T> {
    private final List<T> posts;  // 페이징 처리된 게시글 목록
    private final int limit;   // 페이지 크기
    private final int offset;  // 조회를 건너뛸 데이터 개수
    private final long totalElements;  // db의 총 데이터 개수
    private final int totalPages;     // 총 페이지 개수

    public Pagination(List<T> posts, int limit, int offset, long totalElements) {
        this.posts = posts;
        this.limit = limit;
        this.offset = offset;
        this.totalElements = totalElements;
        this.totalPages = Math.max((int) Math.ceil((double) totalElements / limit), 1);
    }
}
