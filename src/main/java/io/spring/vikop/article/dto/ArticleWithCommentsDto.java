package io.spring.vikop.article.dto;

import io.spring.vikop.comment.dto.CommentDto;
import io.spring.vikop.vote.VoteType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
public class ArticleWithCommentsDto extends ArticleDto {

    private List<CommentDto> comments;

    @Builder(builderMethodName = "dtoWithCommentsBuilder")
    public ArticleWithCommentsDto(Long id,
                                  Timestamp publishingDate,
                                  Long userId,
                                  String username,
                                  String userAvatarUrl,
                                  int votesCount,
                                  VoteType userVote,
                                  String title,
                                  String description,
                                  String thumbnailUrl,
                                  String articleUrl,
                                  List<String> tags,
                                  List<CommentDto> comments) {
        super(id, publishingDate, userId, username, userAvatarUrl, votesCount, userVote, title, description, thumbnailUrl, articleUrl, tags);
        this.comments = comments;
    }
}
