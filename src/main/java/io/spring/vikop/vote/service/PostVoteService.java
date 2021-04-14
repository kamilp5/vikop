package io.spring.vikop.vote.service;

import io.spring.vikop.common.BaseActivity;
import io.spring.vikop.common.ActivityType;
import io.spring.vikop.post.PostService;
import io.spring.vikop.user.UserService;
import io.spring.vikop.vote.Vote;
import io.spring.vikop.vote.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostVoteService extends VoteService {

    private PostService postService;

    public PostVoteService(UserService userService, VoteRepository voteRepository, PostService postService) {
        super(userService, voteRepository);
        this.postService = postService;
    }

    @Override
    public List<Vote> getVotes(Long userId) {
        return voteRepository.findAllByUser_IdAndActivityType(userId, ActivityType.POST);
    }

    @Override
    public List<Vote> getVotes(Long userId, List<Long> postsIds) {
        return voteRepository.findAllByUser_IdAndActivityIdInAndActivityType(userId, postsIds, ActivityType.POST);
    }

    @Override
    Optional<Vote> getVote(Long userId, Long postId) {
        return voteRepository.findByUser_IdAndActivityIdAndActivityType(userId, postId, ActivityType.POST);
    }

    @Override
    BaseActivity getActivity(Long id) {
        return postService.getById(id);
    }
}
