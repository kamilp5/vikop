package io.spring.vikop.vote.service;

import io.spring.vikop.common.BaseActivity;
import io.spring.vikop.user.User;
import io.spring.vikop.user.UserService;
import io.spring.vikop.vote.Vote;
import io.spring.vikop.vote.VoteCommand;
import io.spring.vikop.vote.VoteRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class VoteService {

    protected UserService userService;
    protected VoteRepository voteRepository;


    public abstract List<Vote> getVotes(Long userId);

    public abstract List<Vote> getVotes(Long userId, List<Long> activitiesIds);

    abstract Optional<Vote> getVote(Long userId, Long activityId);

    abstract BaseActivity getActivity(Long id);

    int voteAndReturnVotesCount(VoteCommand voteCommand) {
        User user = userService.getLoggedUser();
        Optional<Vote> found = getVote(user.getId(), voteCommand.getActivityId());
        BaseActivity activity = getActivity(voteCommand.getActivityId());
        if (found.isPresent() && isSameVoteTypeAsPrevious(voteCommand, found.get())) {
            return activity.getVotesCount();
        } else {
            return addOrUpdateVote(voteCommand,
                    found.orElseGet(() -> createVote(voteCommand)),
                    activity);
        }
    }

    private int addOrUpdateVote(VoteCommand voteCommand, Vote vote, BaseActivity activity) {
        vote.setUp(voteCommand.getVoteType());
        activity.applyVote(vote);
        voteRepository.save(vote);
        return activity.getVotesCount();
    }

    private boolean isSameVoteTypeAsPrevious(VoteCommand voteCommand, Vote vote) {
        return  vote.getVoteType().equals(voteCommand.getVoteType());
    }

    private Vote createVote(VoteCommand voteCommand) {
        return new Vote(voteCommand.getActivityId(), voteCommand.getActivityType());
    }
}
