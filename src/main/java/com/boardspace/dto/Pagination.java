package com.boardspace.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Pagination {
    private final int limit;   // 페이지 크기
    private final int offset;  // 조회를 건너뛸 데이터 개수
    @Setter
    private Long userId;

    public Pagination(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
        this.userId = null;
    }
}
