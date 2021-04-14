package io.spring.vikop.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.spring.vikop.common.BaseActivity;
import io.spring.vikop.common.CommentType;
import io.spring.vikop.vote.Vote;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment extends BaseActivity implements Serializable {

    @Column(length = 1024)
    @NotEmpty
    private String content;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "comment_id")
    private List<Vote> votes = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private CommentType commentType;
    private Long activityId;

    @Override
    public void applyVote(Vote vote) {
        this.votes.add(vote);
        setVotesCount(this.getVotesCount() + vote.scoreToApply());
    }
}

