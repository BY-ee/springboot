package com.boardspace.repository;

import com.boardspace.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemoryPostRepository implements PostRepository {
    private final List<Post> posts;

    public Post save(Post post) {
        posts.add(post);
        return posts.get(posts.size() - 1);
    }

    public int delete(Post post) {
        int size = posts.size();
        posts.remove(post);
        return size - posts.size();
    }

    public List<Post> findAllPosts(int start, int end) {
        return posts.subList(start - 1, end - 1);
    }

    public List<Post> findPostsByNickname(int start, int end, String nickname) {
        return posts.stream()
                .filter(post -> post.getNickname().equals(nickname))
                .toList()
                .subList(start, end);
    }

    public void updateById(long id, Post newPost) {
        posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .ifPresent(post -> {
                    post.setTitle(newPost.getTitle());
                    post.setContent(newPost.getContent());
                });
    }

    public long countByNickname(String nickname) {
        return posts.stream()
                .filter(post -> post.getNickname().equals(nickname))
                .count();
    }

    //public List<Post> findAllByOrderByIdDesc(int start, int end) {
    //
    //}
}
