package com.boardspace.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository<T> {
    List<T> findPostsByPage(int start, int size);

    Optional<T> findById(long id);

    List<T> findPostsByPageAndNickname(int start, int size, String nickname);

    void updateById(long id, T newPost);

    void deleteById(long id);

    long countByNickname(String nickname);

    //List<Board> findAllByOrderByIdDesc(int start, int end);

    //@Query(value = "SELECT * FROM (SELECT p1_0.*, ROW_NUMBER() OVER (ORDER BY p1_0.id DESC) AS rn " +
    //        "FROM board p1_0) WHERE rn BETWEEN :start AND :end", nativeQuery = true)
    //List<Board> findAllPosts(@Param("start") int start, @Param("end") int end);

    //List<Board> findAllByOrderByIdDesc();

    //@Query(value = "SELECT * FROM (SELECT p1_0.*, ROW_NUMBER() OVER (ORDER BY p1_0.id DESC) AS rn " +
    //        "FROM board p1_0 WHERE p1_0.nickname = :nickname) WHERE rn BETWEEN :start AND :end", nativeQuery = true)
    //List<Board> findPostsByNickname(@Param("start") int start,
    //                               @Param("end") int end,
    //                               @Param("nickname") String nickname);

    //@Modifying
    //@Transactional
    //@Query(value = "UPDATE board SET title=:title,content=:content WHERE id=:id", nativeQuery = true)
    //void updateById(@Param("id") Long id, @Param("title") String title, @Param("content") String content);

    //long countByNickname(String nickname);

    //@Modifying
    //@Query(value = "ALTER TABLE board DROP CONSTRAINT fk_member_nickname", nativeQuery = true)
    //void dropConstraint();

    //@Modifying
    //@Query(value = "ALTER TABLE board ADD CONSTRAINT fk_member_nickname FOREIGN KEY(nickname) REFERENCES member(nickname)", nativeQuery = true)
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
