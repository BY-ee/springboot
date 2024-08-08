package com.oraclejpa.repository;

import com.oraclejpa.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "SELECT * FROM (SELECT p1_0.*, ROW_NUMBER() OVER (ORDER BY p1_0.id) AS rn " +
                    "FROM post p1_0) WHERE rn BETWEEN :start AND :end", nativeQuery = true)
    List<Post> findAllPosts(@Param("start") int start, @Param("end") int end);
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
