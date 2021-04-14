package io.spring.vikop.post.dto;

import io.spring.vikop.comment.dto.CommentMapper;
import io.spring.vikop.common.Mapper;
import io.spring.vikop.post.Post;
import io.spring.vikop.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PostMapper implements Mapper<Post, PostDto> {

    private CommentMapper commentMapper;

    @Autowired
    public PostMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public PostDto toDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .content(post.getContent())
                .publishingDate(post.getPublishingDate())
                .comments(post.getComments().stream()
                        .map(commentMapper::toDto)
                        .collect(Collectors.toList()))
                .userId(post.getUser().getId())
                .username(post.getUser().getUsername())
                .userAvatarUrl(post.getUser().getUserDetails().getAvatarUrl())
                .votesCount(post.getVotesCount())
                .tags(post.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toList()))
                .userVote(post.getUserVote())
                .build();
    }

    public Post toEntity(NewPostDto newPost) {
        Post post = new Post();
        post.setContent(newPost.getContent());
        return post;
    }

}
