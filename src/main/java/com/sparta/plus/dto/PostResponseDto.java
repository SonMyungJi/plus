package com.sparta.plus.dto;

import com.sparta.plus.entity.Comment;
import com.sparta.plus.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentList = new ArrayList<>();

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        for (Comment comment : post.getComments()) {
            commentList.add(new CommentResponseDto(comment));
        }
    }
}
