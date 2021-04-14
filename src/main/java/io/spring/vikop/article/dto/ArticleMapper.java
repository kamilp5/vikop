package io.spring.vikop.article.dto;

import io.spring.vikop.article.Article;
import io.spring.vikop.comment.dto.CommentMapper;
import io.spring.vikop.common.Mapper;
import io.spring.vikop.tag.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleMapper implements Mapper<Article, ArticleDto> {

    private CommentMapper commentMapper;

    public ArticleMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public ArticleDto toDto(Article article) {
        return ArticleDto.dtoBuilder()
                .id(article.getId())
                .title(article.getTitle())
                .description(article.getDescription())
                .thumbnailUrl(article.getThumbnailUrl())
                .articleUrl(article.getArticleUrl())
                .publishingDate(article.getPublishingDate())
                .userId(article.getUser().getId())
                .username(article.getUser().getUsername())
                .userAvatarUrl(article.getUser().getUserDetails().getAvatarUrl())
                .votesCount(article.getVotesCount())
                .tags(article.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toList()))
                .userVote(article.getUserVote())
                .build();
    }

    public Article toEntity(NewArticleDto articleDto) {
        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setDescription(articleDto.getDescription());
        article.setThumbnailUrl(articleDto.getThumbnailUrl());
        article.setArticleUrl(articleDto.getArticleUrl());
        article.setTags(articleDto.getTags()
                .stream().map(Tag::new)
                .collect(Collectors.toList()));
        return article;
    }

    public List<ArticleSearchResultDto> toSearchResultDto(List<Article> articles) {
        return articles.stream().map(a -> new ArticleSearchResultDto(
                a.getId(),
                a.getTitle(),
                a.getThumbnailUrl()
        )).collect(Collectors.toList());
    }

    public ArticleWithCommentsDto toArticleWithCommentsDto(Article article) {
        return ArticleWithCommentsDto.dtoWithCommentsBuilder()
                .id(article.getId())
                .title(article.getTitle())
                .description(article.getDescription())
                .thumbnailUrl(article.getThumbnailUrl())
                .articleUrl(article.getArticleUrl())
                .publishingDate(article.getPublishingDate())
                .userId(article.getUser().getId())
                .username(article.getUser().getUsername())
                .userAvatarUrl(article.getUser().getUserDetails().getAvatarUrl())
                .votesCount(article.getVotesCount())
                .tags(article.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toList()))
                .userVote(article.getUserVote())
                .comments(article.getComments().stream()
                        .map(commentMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public List<ArticleWithCommentsDto> toArticleWithCommentsDtoList(List<Article> articles) {
        return articles.stream()
                .map(this::toArticleWithCommentsDto)
                .collect(Collectors.toList());
    }
}