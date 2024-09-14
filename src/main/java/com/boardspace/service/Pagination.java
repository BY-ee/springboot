package com.boardspace.service;

import lombok.Getter;

import java.util.List;

@Getter
public class Pagination<T> {
    private final List<T> content;
    private final int number; // 현재 페이지 번호
    private final int size;   // 페이지 크기
    private final long totalElements; // 총 게시글 수
    private final int totalPages;

    public Pagination(List<T> content, int number, int size, long totalElements) {
        this.content = content;
        this.number = number;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil(totalElements / (double) size);
    }
}
