package com.sparta.plus.controller;

import com.sparta.plus.auth.UserDetailsImpl;
import com.sparta.plus.dto.ApiResponseDto;
import com.sparta.plus.dto.CommentRequestDto;
import com.sparta.plus.dto.CommentResponseDto;
import com.sparta.plus.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comment")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto result = commentService.createComment(postId, requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto result = commentService.updateComment(id, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(id, userDetails.getUser());
        return ResponseEntity.ok().body(new ApiResponseDto("댓글이 삭제되었습니다", HttpStatus.OK.value()));
    }
}
