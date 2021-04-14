package io.spring.vikop.vote;

import io.spring.vikop.common.ActivityType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class VoteCommand {

    @NotNull
    private Long activityId;
    @NotNull
    private VoteType voteType;
    @NotNull
    private ActivityType activityType;

}

