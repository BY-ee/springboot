package com.boardspace.repository;

import com.boardspace.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository {
    List<Post> findAllPosts(int start, int end);

    List<Post> findPostsByNickname(int start, int end, String nickname);

    void updateById(long id, Post newPost);

    long countByNickname(String nickname);

    //List<Post> findAllByOrderByIdDesc(int start, int end);

    //@Query(value = "SELECT * FROM (SELECT p1_0.*, ROW_NUMBER() OVER (ORDER BY p1_0.id DESC) AS rn " +
    //        "FROM post p1_0) WHERE rn BETWEEN :start AND :end", nativeQuery = true)
    //List<Post> findAllPosts(@Param("start") int start, @Param("end") int end);

    //List<Post> findAllByOrderByIdDesc();

    //@Query(value = "SELECT * FROM (SELECT p1_0.*, ROW_NUMBER() OVER (ORDER BY p1_0.id DESC) AS rn " +
    //        "FROM post p1_0 WHERE p1_0.nickname = :nickname) WHERE rn BETWEEN :start AND :end", nativeQuery = true)
    //List<Post> findPostsByNickname(@Param("start") int start,
    //                               @Param("end") int end,
    //                               @Param("nickname") String nickname);

    //@Modifying
    //@Transactional
    //@Query(value = "UPDATE post SET title=:title,content=:content WHERE id=:id", nativeQuery = true)
    //void updateById(@Param("id") Long id, @Param("title") String title, @Param("content") String content);

    //long countByNickname(String nickname);

    //@Modifying
    //@Query(value = "ALTER TABLE post DROP CONSTRAINT fk_member_nickname", nativeQuery = true)
    //void dropConstraint();

    //@Modifying
    //@Query(value = "ALTER TABLE post ADD CONSTRAINT fk_member_nickname FOREIGN KEY(nickname) REFERENCES member(nickname)", nativeQuery = true)
    //void addConstraint();

    //@Modifying
    //@Transactional
    //@Query(value = "DROP SEQUENCE post_seq", nativeQuery = true)
    //void dropPostSequence();

    //@Modifying
    //@Transactional
    //@Query(value = "CREATE SEQUENCE post_seq START WITH 1 INCREMENT BY 1 NOCACHE", nativeQuery = true)
    //void createPostSequence();
}
