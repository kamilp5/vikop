package io.spring.vikop.comment.dto;

import io.spring.vikop.comment.Comment;
import io.spring.vikop.common.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper implements Mapper<Comment, CommentDto> {

    @Override
    public CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .activityId(comment.getActivityId())
                .content(comment.getContent())
                .publishingDate(comment.getPublishingDate())
                .userId(comment.getUser().getId())
                .username(comment.getUser().getUsername())
                .userAvatarUrl(comment.getUser().getUserDetails().getAvatarUrl())
                .votesCount(comment.getVotesCount())
                .userVote(comment.getUserVote())
                .commentType(comment.getCommentType())
                .build();
    }

    public Comment toEntity(NewCommentDto commentDto) {
        Comment comment = new Comment();
        comment.setActivityId(commentDto.getActivityId());
        comment.setContent(commentDto.getContent());
        comment.setCommentType(commentDto.getCommentType());
        return comment;
    }
}