package com.boardspace.mapper;

import com.boardspace.dto.PostDTO;
import com.boardspace.model.QnAPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper{
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "createdAt", target = "createdAt", ignore = true)
    @Mapping(source = "updatedAt", target = "updatedAt", ignore = true)
    PostDTO ToPostDTO(QnAPost qnAPost);
}
