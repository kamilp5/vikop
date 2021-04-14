package io.spring.vikop.vote.service;

import io.spring.vikop.article.ArticleService;
import io.spring.vikop.common.BaseActivity;
import io.spring.vikop.common.ActivityType;
import io.spring.vikop.user.UserService;
import io.spring.vikop.vote.Vote;
import io.spring.vikop.vote.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleVoteService extends VoteService {

    private ArticleService articleService;

    public ArticleVoteService(UserService userService, VoteRepository voteRepository, ArticleService articleService) {
        super(userService, voteRepository);
        this.articleService = articleService;
    }

    @Override
    public List<Vote> getVotes(Long userId) {
        return voteRepository.findAllByUser_IdAndActivityType(userId, ActivityType.ARTICLE);
    }

    @Override
    public List<Vote> getVotes(Long userId, List<Long> articlesIds) {
        return voteRepository.findAllByUser_IdAndActivityIdInAndActivityType(userId, articlesIds, ActivityType.ARTICLE);
    }

    @Override
    Optional<Vote> getVote(Long userId, Long activityId) {
        return voteRepository.findByUser_IdAndActivityIdAndActivityType(userId, activityId, ActivityType.ARTICLE);
    }

    @Override
    BaseActivity getActivity(Long id) {
        return articleService.getById(id);
    }
}
