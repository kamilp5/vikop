package io.spring.vikop.profile;

import io.spring.vikop.article.ArticleService;
import io.spring.vikop.article.dto.ArticleDto;
import io.spring.vikop.article.dto.ArticleWithCommentsDto;
import io.spring.vikop.comment.ArticleCommentService;
import io.spring.vikop.comment.PostCommentService;
import io.spring.vikop.comment.dto.CommentDto;
import io.spring.vikop.post.PostService;
import io.spring.vikop.post.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ProfileService {

    private ArticleService articleService;
    private PostService postService;
    private PostCommentService postCommentService;
    private ArticleCommentService articleCommentService;

    public ProfileService(ArticleService articleService,
                          PostService postService,
                          PostCommentService postCommentService,
                          ArticleCommentService articleCommentService) {
        this.articleService = articleService;
        this.postService = postService;
        this.postCommentService = postCommentService;
        this.articleCommentService = articleCommentService;
    }

    List<ArticleDto> getUserArticles(Long userId) {
        return articleService.getAllByUserId(userId);
    }

    List<ArticleDto> getUserArticlesVotes(Long userId) {
        return articleService.getVotedByUser(userId);
    }

    List<ArticleWithCommentsDto> getArticlesWithUserComments(Long userId) {
        return articleService.getArticlesWithUserComments(userId);
    }

    List<CommentDto> getUserArticlesCommentsVotes(Long userId) {
        return articleCommentService.getUserVotedComments(userId);
    }

    List<PostDto> getUserPosts(Long userId) {
        return postService.getAllByUserId(userId);
    }

    List<PostDto> getPostsWithUserVotes(Long userId) {
        return postService.getVotedByUser(userId);
    }

    List<PostDto> getPostWithUserComments(Long userId) {
        return postService.getPostsWithUserComments(userId);
    }

    List<CommentDto> getUserPostsCommentsVotes(Long userId) {
        return postCommentService.getUserVotedComments(userId);
    }
}
