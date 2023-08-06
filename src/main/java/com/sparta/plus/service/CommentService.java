package com.sparta.plus.service;

import com.sparta.plus.dto.CommentRequestDto;
import com.sparta.plus.dto.CommentResponseDto;
import com.sparta.plus.entity.Comment;
import com.sparta.plus.entity.Post;
import com.sparta.plus.entity.User;
import com.sparta.plus.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, User user) {
        Post post = postService.findPost(postId);
        Comment comment = new Comment(requestDto.getBody(), user);
        comment.setPost(post);
        return new CommentResponseDto(commentRepository.save(comment));
    }

    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {
        Comment comment = findComment(id);

        if(!comment.getUser().equals(user)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        comment.setBody(requestDto.getBody());

        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long id, User user) {
        Comment comment = findComment(id);

        if (!comment.getUser().equals(user)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        commentRepository.delete(comment);
    }

    public Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));
    }
}
