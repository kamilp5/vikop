package io.spring.vikop.vote.service;

import io.spring.vikop.vote.VoteCommand;
import io.spring.vikop.vote.Voter;
import org.springframework.stereotype.Component;

@Component
public class VoteServiceAdapter implements Voter {

    private ArticleVoteService articleVoteService;
    private PostVoteService postVoteService;
    private ArticleCommentVoteService articleCommentVoteService;
    private PostCommentVoteService postCommentVoteService;

    public VoteServiceAdapter(ArticleVoteService articleVoteService,
                              PostVoteService postVoteService,
                              ArticleCommentVoteService articleCommentVoteService,
                              PostCommentVoteService postCommentVoteService) {
        this.articleVoteService = articleVoteService;
        this.postVoteService = postVoteService;
        this.articleCommentVoteService = articleCommentVoteService;
        this.postCommentVoteService = postCommentVoteService;
    }

    @Override
    public int voteAndReturnVotesCount(VoteCommand voteCommand) {
        switch (voteCommand.getActivityType()) {
            case ARTICLE:
                return articleVoteService.voteAndReturnVotesCount(voteCommand);
            case POST:
                return postVoteService.voteAndReturnVotesCount(voteCommand);
            case ARTICLE_COMMENT:
                return articleCommentVoteService.voteAndReturnVotesCount(voteCommand);
            default:
                return postCommentVoteService.voteAndReturnVotesCount(voteCommand);
        }
    }
}
