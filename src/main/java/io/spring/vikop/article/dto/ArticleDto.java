package io.spring.vikop.article.dto;

import io.spring.vikop.common.ActivityDto;
import io.spring.vikop.vote.VoteType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
public class ArticleDto extends ActivityDto {

    private String title;
    private String description;
    private String thumbnailUrl;
    private String articleUrl;
    private List<String> tags;

    @Builder(builderMethodName = "dtoBuilder")
    public ArticleDto(Long id,
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
                      List<String> tags) {
        super(id, publishingDate, userId, username, userAvatarUrl, votesCount, userVote);
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.articleUrl = articleUrl;
        this.tags = tags;
    }
}
