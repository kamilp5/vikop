package io.spring.vikop.comment;

import io.spring.vikop.comment.dto.CommentDto;
import io.spring.vikop.comment.dto.CommentMapper;
import io.spring.vikop.comment.dto.NewCommentDto;
import io.spring.vikop.comment.exception.CommentNotFoundException;
import io.spring.vikop.common.*;
import io.spring.vikop.user.UserService;
import io.spring.vikop.vote.*;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class CommentService<R extends MainActivity> extends BaseActivityService<Comment> {

    protected CommentRepository commentRepository;
    protected CommentMapper mapper;
    private ActivityService<R> activityService;

    void setActivityService(ActivityService<R> activityService) {
        this.activityService = activityService;
    }

    public CommentService(CommentRepository repository,
                          UserService userService,
                          CommentMapper mapper) {
        super(repository, userService);
        this.commentRepository = repository;
        this.mapper = mapper;
    }

    public abstract List<Comment> getUserComments(Long id);

    @Override
    public Supplier<NotFoundException> activityNotFound(Long id) {
        return () -> new CommentNotFoundException(id);
    }

    public CommentDto addComment(NewCommentDto newCommentDto) {
        Comment comment = mapper.toEntity(newCommentDto);
        R activity = activityService.getById(comment.getActivityId());
        activity.addComment(comment);
        repository.save(comment);
        return mapper.toDto(comment);
    }

    public List<CommentDto> getUserVotedComments(Long userId) {
        List<Vote> votes = voteService.getVotes(userId);
        List<Long> commentsIds = votes.stream().map(Vote::getActivityId).collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllById(commentsIds);
        comments.forEach(c -> c.matchVote(votes));
        return mapper.toDtoList(comments);
    }
}