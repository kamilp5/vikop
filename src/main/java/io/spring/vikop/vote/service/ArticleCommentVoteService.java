package io.spring.vikop.vote.service;

import io.spring.vikop.comment.ArticleCommentService;
import io.spring.vikop.common.ActivityType;
import io.spring.vikop.user.UserService;
import io.spring.vikop.vote.Vote;
import io.spring.vikop.vote.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleCommentVoteService extends CommentVoteService {

    public ArticleCommentVoteService(UserService userService,
                                     VoteRepository voteRepository,
                                     ArticleCommentService commentService) {
        super(userService, voteRepository, commentService);
    }

    @Override
    public List<Vote> getVotes(Long userId) {
        return voteRepository.findAllByUser_IdAndActivityType(userId, ActivityType.ARTICLE_COMMENT);
    }

    @Override
    public List<Vote> getVotes(Long userId, List<Long> commentsIds) {
        return voteRepository.findAllByUser_IdAndActivityIdInAndActivityType(userId, commentsIds, ActivityType.ARTICLE_COMMENT);
    }

    @Override
    Optional<Vote> getVote(Long userId, Long articleCommentId) {
        return voteRepository.findByUser_IdAndActivityIdAndActivityType(userId, articleCommentId, ActivityType.ARTICLE_COMMENT);
    }
}
