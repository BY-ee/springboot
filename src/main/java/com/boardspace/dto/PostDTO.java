package com.boardspace.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private long id;
    private long userId;
    private String userNickname;
    private String title;
    private String content;
    private String topic;
    private String tag;
    private int viewCount;
    private long createdAt;
    private long updatedAt;
}
