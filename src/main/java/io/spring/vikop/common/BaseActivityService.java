package io.spring.vikop.common;

import io.spring.vikop.user.User;
import io.spring.vikop.user.UserService;
import io.spring.vikop.vote.Vote;
import io.spring.vikop.vote.service.VoteService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public abstract class BaseActivityService<R extends Voteable> implements ActivityService<R> {

    protected ActivityRepository<R> repository;
    protected VoteService voteService;
    protected UserService userService;

    public void setVoteService(VoteService voteService) {
        this.voteService = voteService;
    }

    public BaseActivityService(ActivityRepository<R> repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public void save(R r) {
        repository.save(r);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public R getById(Long id) {
        return repository.findById(id)
                .orElseThrow(activityNotFound(id));
    }

    @Override
    public void findAndMatchUserVotes(Collection<? extends BaseActivity> activities) {
        if (userService.isUserLogged() && activities.size() > 0) {
            User loggedUser = userService.getLoggedUser();
            List<Long> activitiesIds = activities.stream().map(BaseActivity::getId).collect(Collectors.toList());
            List<Vote> votes = voteService.getVotes(loggedUser.getId(), activitiesIds);
            activities.forEach(a -> a.matchVote(votes));
        }
    }
}
