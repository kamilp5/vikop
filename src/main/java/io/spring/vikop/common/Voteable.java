package io.spring.vikop.common;

import io.spring.vikop.vote.Vote;

import java.util.List;

public interface Voteable {

    void applyVote(Vote vote);

    void matchVote(List<Vote> votes);
}