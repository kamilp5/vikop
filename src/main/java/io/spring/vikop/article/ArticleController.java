package io.spring.vikop.article;

import io.spring.vikop.article.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController{

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDto addArticle(@Valid @RequestBody NewArticleDto article) {
        return articleService.add(article);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @authorizationExpressions.isArticleOwner(#id, authentication)")
    public void deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
    }

    @GetMapping("/{id}")
    public ArticleWithCommentsDto getArticleById(@PathVariable Long id) {
        return articleService.getDtoById(id);
    }

    @GetMapping("/search/{term}")
    public List<ArticleSearchResultDto> searchArticles(@PathVariable String term) {
        return articleService.searchArticles(term);
    }

    @GetMapping
    public Page<ArticleDto> getPage(Pageable pageable) {
        return articleService.getPage(pageable);
    }

    @GetMapping("/hot")
    public Page<ArticleDto> getArticlesLastByVotesCountLast24h(Pageable pageable) {
        return articleService.getPageByVotesCountLast24h(pageable);
    }

    @GetMapping("/followed_tags")
    public List<ArticleDto> getArticlesByFollowedTags() {
        return articleService.getAllByUserFollowedTags();
    }

    @GetMapping("/tag/{tagName}")
    public List<ArticleDto> getArticlesByTagName(@PathVariable String tagName) {
        return articleService.getAllByTagName(tagName);
    }
}
