package io.spring.vikop.article;

import io.spring.vikop.article.dto.*;
import io.spring.vikop.article.exception.ArticleNotFoundException;
import io.spring.vikop.comment.ArticleCommentService;
import io.spring.vikop.common.*;
import io.spring.vikop.tag.TagService;
import io.spring.vikop.user.UserService;
import io.spring.vikop.vote.service.ArticleVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

@Service
public class ArticleService extends MainActivityService<Article, ArticleDto> {

    private ArticleRepository articleRepository;
    private TagService tagService;
    private ArticleMapper articleMapper;

    @Autowired
    public void setVoteService(ArticleVoteService voteService) {
        this.voteService = voteService;
    }

    @Autowired
    public void setCommentService(ArticleCommentService commentService) {
        super.setCommentService(commentService);
    }

    public ArticleService(ArticleRepository repository,
                          UserService userService,
                          ArticleMapper mapper,
                          ArticleRepository articleRepository,
                          TagService tagService,
                          ArticleMapper articleMapper) {
        super(repository, userService, mapper);
        this.articleRepository = articleRepository;
        this.tagService = tagService;
        this.articleMapper = articleMapper;
    }

    @Override
    public Supplier<NotFoundException> activityNotFound(Long id) {
        return () -> new ArticleNotFoundException(id);
    }

    ArticleDto add(NewArticleDto articleDto) {
        Article article = articleMapper.toEntity(articleDto);
        article.setTags(tagService.getTagsFromDBOrSave(article.getTags()));
        save(article);
        return articleMapper.toDto(article);
    }

    List<ArticleSearchResultDto> searchArticles(String term) {
        List<Article> articles = articleRepository.findByTitle(term);
        return articleMapper.toSearchResultDto(articles);
    }

    ArticleWithCommentsDto getDtoById(Long id) {
        Article article = getById(id);
        setUserVotes(Collections.singletonList(article));
        return articleMapper.toArticleWithCommentsDto(article);
    }

    public List<ArticleWithCommentsDto> getArticlesWithUserComments(Long userId) {
        List<Article> articles = getActivitiesWithUserComments(userId);
        return articleMapper.toArticleWithCommentsDtoList(articles);
    }
}
