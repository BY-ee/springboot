package com.boardspace.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardMapper<T> {
    List<T> findPostsByPage(int start, int size);

    Optional<T> findById(long id);

    List<T> findPostsByPageAndNickname(int start, int size, String nickname);

    void updateById(long id, T newPost);

    void deleteById(long id);

    long countByNickname(String nickname);
}