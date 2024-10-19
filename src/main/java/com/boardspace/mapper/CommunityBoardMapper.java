package com.boardspace.mapper;

import com.boardspace.model.CommunityPost;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityBoardMapper extends BoardMapper<CommunityPost> {
}
