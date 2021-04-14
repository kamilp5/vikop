package io.spring.vikop.vote.service;

import io.spring.vikop.comment.PostCommentService;
import io.spring.vikop.common.ActivityType;
import io.spring.vikop.user.UserService;
import io.spring.vikop.vote.Vote;
import io.spring.vikop.vote.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostCommentVoteService extends CommentVoteService {

    public PostCommentVoteService(UserService userService,
                                  VoteRepository voteRepository,
                                  PostCommentService commentService) {
        super(userService, voteRepository, commentService);
    }

    @Override
    public List<Vote> getVotes(Long userId) {
        return voteRepository.findAllByUser_IdAndActivityType(userId, ActivityType.POST_COMMENT);
    }

    @Override
    public List<Vote> getVotes(Long userId, List<Long> commentsIds) {
        return voteRepository.findAllByUser_IdAndActivityIdInAndActivityType(userId, commentsIds, ActivityType.POST_COMMENT);
    }

    @Override
    Optional<Vote> getVote(Long userId, Long postCommentId) {
        return voteRepository.findByUser_IdAndActivityIdAndActivityType(userId, postCommentId, ActivityType.POST_COMMENT);
    }
}
