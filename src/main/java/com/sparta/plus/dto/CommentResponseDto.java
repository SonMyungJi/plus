package com.sparta.plus.dto;

import com.sparta.plus.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {
    private Long id;
    private String body;
    private String nickname;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.body = comment.getBody();
        this.nickname = comment.getUser().getNickname();
    }
}
