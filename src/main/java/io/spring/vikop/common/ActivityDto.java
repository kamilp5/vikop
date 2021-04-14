package io.spring.vikop.common;

import io.spring.vikop.vote.VoteType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ActivityDto {

    private Long id;
    private Timestamp publishingDate;
    private Long userId;
    private String username;
    private String userAvatarUrl;
    private int votesCount;
    private VoteType userVote;
}
