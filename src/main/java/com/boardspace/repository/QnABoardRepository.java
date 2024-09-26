package com.boardspace.repository;

import com.boardspace.model.QnABoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QnABoardRepository implements BoardRepository<QnABoard> {
    private final List<QnABoard> qnAPosts;

    // 게시글 작성
    public void save(QnABoard post) {
        qnAPosts.add(post);
    }

    // 게시글 삭제
    public int delete(QnABoard post) {
        int size = qnAPosts.size();
        qnAPosts.remove(post);
        return size - qnAPosts.size();
    }

    // 고유 id에 해당하는 게시글을 삭제
    public void deleteById(long id) {
        qnAPosts.removeIf(post -> post.getId().equals(id));
    }

    // 모든 게시글을 조회
    public List<QnABoard> findPostsByPage(int start, int size) {
        int end = Math.min(start + size, qnAPosts.size());
        return qnAPosts.subList(start, end);
    }

    // 고유 id에 해당하는 게시글을 조회
    public Optional<QnABoard> findById(long id) {
        return qnAPosts.stream().filter(post -> post.getId().equals(id)).findAny();
    }

    // 게시글 작성자에 해당하는 모든 게시글을 조회
    public List<QnABoard> findPostsByPageAndNickname(int start, int size, String nickname) {
        List<QnABoard> postsByNickname = qnAPosts.stream().filter(post -> post.getNickname().equals(nickname)).toList();
        int end = Math.min(start + size, postsByNickname.size());
        return postsByNickname.subList(start, end);
    }

    // 고유 id에 해당하는 게시글을 수정
    public void updateById(long id, QnABoard newPost) {
        qnAPosts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .ifPresent(post -> {
                    post.setTitle(newPost.getTitle());
                    post.setContent(newPost.getContent());
                });
    }

    // 총 게시글 개수
    public long count() {
        return qnAPosts.size();
    }

    // 게시글 작성자가 작성한 총 게시글 개수
    public long countByNickname(String nickname) {
        return qnAPosts.stream()
                .filter(post -> post.getNickname().equals(nickname))
                .count();
    }
}