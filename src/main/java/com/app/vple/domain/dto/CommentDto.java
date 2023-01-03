package com.app.vple.domain.dto;

import com.app.vple.domain.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private Long id;

    private String content;

    private String nickname;

    private LocalDateTime createdDate;

    public CommentDto(Comment entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.nickname = entity.getNickname();
        this.createdDate = entity.getCreatedDate();
    }
}
