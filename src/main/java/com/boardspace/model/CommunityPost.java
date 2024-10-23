package com.boardspace.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Getter
@Setter
@Alias("Community")
public class CommunityPost {
    private long id;
    private long userId;
    private String userNickname;
    private String title;
    private String content;
    private String topic;
    private String tag;
    private int viewCount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}