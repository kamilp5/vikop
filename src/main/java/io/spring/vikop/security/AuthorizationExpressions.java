package io.spring.vikop.security;

import io.spring.vikop.article.ArticleService;
import io.spring.vikop.comment.ArticleCommentService;
import io.spring.vikop.post.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationExpressions {

    private ArticleService articleService;
    private PostService postService;
    private ArticleCommentService commentService;

    public AuthorizationExpressions(ArticleService articleService,
                                    PostService postService,
                                    ArticleCommentService commentService) {
        this.articleService = articleService;
        this.postService = postService;
        this.commentService = commentService;
    }

    public boolean isArticleOwner(Long id, Authentication authentication) {
        String username = articleService.getById(id).getUser().getUsername();
        return username.equals(authentication.getName());
    }

    public boolean isPostOwner(Long id, Authentication authentication) {
        String username = postService.getById(id).getUser().getUsername();
        return username.equals(authentication.getName());
    }

    public boolean isCommentOwner(Long id, Authentication authentication) {
        String username = commentService.getById(id).getUser().getUsername();
        return username.equals(authentication.getName());
    }
}
