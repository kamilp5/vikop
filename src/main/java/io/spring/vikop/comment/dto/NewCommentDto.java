package io.spring.vikop.comment.dto;

import io.spring.vikop.common.CommentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewCommentDto {

    @NotNull
    private Long activityId;
    @NotNull
    private CommentType commentType;
    @NotEmpty
    private String content;
}