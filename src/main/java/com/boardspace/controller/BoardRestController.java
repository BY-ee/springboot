package com.boardspace.controller;

import com.boardspace.model.CommunityPost;
import com.boardspace.model.QnAPost;
import com.boardspace.service.CommunityBoardService;
import com.boardspace.service.QnABoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardRestController {
    private final QnABoardService qnABoardService;
    private final CommunityBoardService commBoardService;

    @GetMapping("qna/update/{id}")
    public ResponseEntity<QnAPost> selectQnAPost(@PathVariable long id) {
        return ResponseEntity.ok(qnABoardService.findById(id).orElseThrow());
    }

    @GetMapping("community/update/{id}")
    public ResponseEntity<CommunityPost> selectCommPost(@PathVariable long id) {
        return ResponseEntity.ok(commBoardService.findById(id).orElseThrow());
    }
}
