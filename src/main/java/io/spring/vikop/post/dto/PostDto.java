package io.spring.vikop.post.dto;

import io.spring.vikop.comment.dto.CommentDto;
import io.spring.vikop.common.ActivityDto;
import io.spring.vikop.vote.VoteType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostDto extends ActivityDto {

    private String content;
    private List<CommentDto> comments;
    private List<String> tags;

    @Builder
    public PostDto(Long id,
                   Timestamp publishingDate,
                   Long userId,
                   String username,
                   String userAvatarUrl,
                   int votesCount,
                   VoteType userVote,
                   String content,
                   List<CommentDto> comments,
                   List<String> tags) {
        super(id, publishingDate, userId, username, userAvatarUrl, votesCount, userVote);
        this.content = content;
        this.comments = comments;
        this.tags = tags;
    }
}
