package io.spring.vikop.vote;

public interface Voter {
    int voteAndReturnVotesCount(VoteCommand voteCommand);
}
