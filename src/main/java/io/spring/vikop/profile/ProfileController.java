package io.spring.vikop.profile;

import io.spring.vikop.article.dto.ArticleDto;
import io.spring.vikop.article.dto.ArticleWithCommentsDto;
import io.spring.vikop.comment.dto.CommentDto;
import io.spring.vikop.post.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/profile")
public class ProfileController {

    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{userId}/articles")
    public List<ArticleDto> getUserArticles(@PathVariable Long userId) {
        return profileService.getUserArticles(userId);
    }

    @GetMapping("/{userId}/articles/votes")
    public List<ArticleDto> getUserArticlesVotes(@PathVariable Long userId) {
        return profileService.getUserArticlesVotes(userId);
    }

    @GetMapping("/{userId}/articles/comments")
    public List<ArticleWithCommentsDto> getUserArticlesComments(@PathVariable Long userId) {
        return profileService.getArticlesWithUserComments(userId);
    }

    @GetMapping("/{userId}/articles/comments/votes")
    public List<CommentDto> getUserArticlesCommentsVotes(@PathVariable Long userId) {
        return profileService.getUserArticlesCommentsVotes(userId);
    }

    @GetMapping("/{userId}/posts")
    public List<PostDto> getUserPosts(@PathVariable Long userId) {
        return profileService.getUserPosts(userId);
    }

    @GetMapping("/{userId}/posts/votes")
    public List<PostDto> getPostsWithUserVotes(@PathVariable Long userId) {
        return profileService.getPostsWithUserVotes(userId);
    }

    @GetMapping("/{userId}/posts/comments")
    public List<PostDto> getUserPostsComments(@PathVariable Long userId) {
        return profileService.getPostWithUserComments(userId);
    }

    @GetMapping("/{userId}/posts/comments/votes")
    public List<CommentDto> getUserPostsCommentsVotes(@PathVariable Long userId) {
        return profileService.getUserPostsCommentsVotes(userId);
    }
}
