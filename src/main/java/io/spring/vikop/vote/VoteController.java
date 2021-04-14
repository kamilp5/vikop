package io.spring.vikop.vote;

import io.spring.vikop.vote.service.VoteServiceAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class VoteController {

    private VoteServiceAdapter voteServiceAdapter;

    public VoteController(VoteServiceAdapter voteServiceAdapter) {
        this.voteServiceAdapter = voteServiceAdapter;
    }

    @PostMapping("/api/vote")
    public int vote(@Valid @RequestBody VoteCommand vote) {
        return voteServiceAdapter.voteAndReturnVotesCount(vote);
    }
}