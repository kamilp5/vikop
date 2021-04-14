package io.spring.vikop.comment.dto;

import io.spring.vikop.common.ActivityDto;
import io.spring.vikop.common.CommentType;
import io.spring.vikop.vote.VoteType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class CommentDto extends ActivityDto {

    private Long activityId;
    private CommentType commentType;
    private String content;

    @Builder
    public CommentDto(Long id,
                      Timestamp publishingDate,
                      Long userId,
                      String username,
                      String userAvatarUrl,
                      int votesCount,
                      VoteType userVote,
                      CommentType commentType,
                      Long activityId,
                      String content) {
        super(id, publishingDate, userId, username, userAvatarUrl, votesCount, userVote);
        this.commentType = commentType;
        this.activityId = activityId;
        this.content = content;
    }
}
