package io.spring.vikop.vote.service;

import io.spring.vikop.comment.CommentService;
import io.spring.vikop.common.BaseActivity;
import io.spring.vikop.common.MainActivity;
import io.spring.vikop.user.UserService;
import io.spring.vikop.vote.VoteRepository;

public abstract class CommentVoteService<R extends MainActivity> extends VoteService {

    private CommentService<R> commentService;

    public CommentVoteService(UserService userService, VoteRepository voteRepository, CommentService<R> commentService) {
        super(userService, voteRepository);
        this.commentService = commentService;
    }

    @Override
    BaseActivity getActivity(Long id) {
        return commentService.getById(id);
    }
}
