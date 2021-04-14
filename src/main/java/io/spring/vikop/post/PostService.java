package io.spring.vikop.post;

import io.spring.vikop.comment.PostCommentService;
import io.spring.vikop.common.*;
import io.spring.vikop.post.dto.NewPostDto;
import io.spring.vikop.post.dto.PostDto;
import io.spring.vikop.post.dto.PostMapper;
import io.spring.vikop.post.exception.PostNotFoundException;
import io.spring.vikop.user.UserService;
import io.spring.vikop.tag.Tag;
import io.spring.vikop.tag.TagService;
import io.spring.vikop.vote.service.PostVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

@Service
public class PostService extends MainActivityService<Post, PostDto> {

    private TagService tagService;
    private PostMapper postMapper;

    @Autowired
    public void setCommentService(PostCommentService commentService) {
        super.setCommentService(commentService);
    }

    @Autowired
    public void setVoteService(PostVoteService voteService) {
        super.setVoteService(voteService);
    }

    public PostService(PostMapper mapper,
                       UserService userService,
                       PostRepository repository,
                       TagService tagService) {
        super(repository, userService, mapper);
        this.tagService = tagService;
        this.postMapper = mapper;
    }

    @Override
    public Supplier<NotFoundException> activityNotFound(Long id) {
        return () -> new PostNotFoundException(id);
    }

    PostDto add(NewPostDto newPost) {
        Post post = postMapper.toEntity(newPost);
        List<Tag> foundTags = tagService.getTagsFromContent(post.getContent());
        post.setTags(tagService.getTagsFromDBOrSave(foundTags));
        repository.save(post);
        return mapper.toDto(post);
    }

    PostDto getDtoById(Long id) {
        Post post = getById(id);
        setUserVotes(Collections.singletonList(post));
        return mapper.toDto(post);
    }

    public List<PostDto> getPostsWithUserComments(Long userId) {
        List<Post> posts = getActivitiesWithUserComments(userId);
        return mapper.toDtoList(posts);
    }
}
