package com.boardspace.repository;

import com.boardspace.model.CommunityPost;
import com.boardspace.model.QnAPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemoryBoardRepository implements BoardMapper<CommunityPost> {
    private final List<CommunityPost> commPosts;
    private final List<QnAPost> qnAPosts;

    // 게시글 작성
    public void save(CommunityPost post) {
        commPosts.add(post);
    }

    // 게시글 삭제
    public int delete(CommunityPost post) {
        int size = commPosts.size();
        commPosts.remove(post);
        return size - commPosts.size();
    }

    // 고유 id에 해당하는 게시글을 삭제
    public void deleteById(long id) {
        commPosts.removeIf(post -> post.getId().equals(id));
    }

    // 모든 게시글을 조회
    public List<CommunityPost> findPostsByPage(int start, int size) {
        int end = Math.min(start + size, commPosts.size());
        return commPosts.subList(start, end);
    }

    // 고유 id에 해당하는 게시글을 조회
    public Optional<CommunityPost> findById(long id) {
        return commPosts.stream().filter(post -> post.getId().equals(id)).findAny();
    }

    // 게시글 작성자에 해당하는 모든 게시글을 조회
    public List<CommunityPost> findPostsByPageAndNickname(int start, int size, String nickname) {
        List<CommunityPost> postsByNickname = commPosts.stream().filter(post -> post.getNickname().equals(nickname)).toList();
        int end = Math.min(start + size, postsByNickname.size());
        return postsByNickname.subList(start, end);
    }

    // 고유 id에 해당하는 게시글을 수정
    public void updateById(long id, CommunityPost newPost) {
        commPosts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .ifPresent(post -> {
                    post.setTitle(newPost.getTitle());
                    post.setContent(newPost.getContent());
                });
    }

    // 총 게시글 개수
    public long count() {
        return commPosts.size();
    }

    // 게시글 작성자가 작성한 총 게시글 개수
    public long countByNickname(String nickname) {
        return commPosts.stream()
                .filter(post -> post.getNickname().equals(nickname))
                .count();
    }
}