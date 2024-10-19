package com.boardspace.mapper;

import com.boardspace.model.QnAPost;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnABoardMapper extends BoardMapper<QnAPost> {
}
