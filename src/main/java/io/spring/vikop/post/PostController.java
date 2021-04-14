package io.spring.vikop.post;

import io.spring.vikop.post.dto.NewPostDto;
import io.spring.vikop.post.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addPost(@Valid @RequestBody NewPostDto post) {
        return postService.add(post);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @authorizationExpressions.isPostOwner(#id, authentication)")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId) {
        return postService.getDtoById(postId);
    }

    @GetMapping
    public Page<PostDto> getAllPostsByPageSortedByVotesCount(Pageable pageable) {
        return postService.getPage(pageable);
    }

    @GetMapping("/hot")
    public Page<PostDto> getPostsLast24hByVotesCount(Pageable pageable) {
        return postService.getPageByVotesCountLast24h(pageable);
    }

    @GetMapping("/tag/{tagName}")
    public List<PostDto> getPostsByTagName(@PathVariable String tagName) {
        return postService.getAllByTagName(tagName);
    }

    @GetMapping("/followed_tags")
    public List<PostDto> getPostsByFollowedTags() {
        return postService.getAllByUserFollowedTags();
    }
}
