package com.boardspace.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommunityPost {
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    //@SequenceGenerator(name = "post_seq", sequenceName = "post_seq", allocationSize = 1)
    private Long id;
    private String nickname;
    private String title;
    private String content;
    private int viewCount;
    private Timestamp createTime;
    private Timestamp updateTime;
}