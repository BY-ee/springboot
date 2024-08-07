package com.oraclejpa.repository;

import com.oraclejpa.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByIdDesc();

    @Modifying
    @Transactional
    @Query(value = "UPDATE post SET title=:title,content=:content WHERE id=:id", nativeQuery = true)
    void updateById(@Param("id") Long id, @Param("title") String title, @Param("content") String content);
// @Modifying
// @Transactional
// @Query(value = "DROP SEQUENCE post_seq", nativeQuery = true)
// void dropPostSequence();
//
// @Modifying
// @Transactional
// @Query(value = "CREATE SEQUENCE post_seq START WITH 1 INCREMENT BY 1 NOCACHE", nativeQuery = true)
// void createPostSequence();
}
