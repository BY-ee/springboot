package com.boardspace.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Getter
@Setter
@Alias("qna")
public class QnAPost {
    private Long id;
    private String nickname;
    private String title;
    private String content;
    private String topic;
    private String tag;
    private int viewCount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}