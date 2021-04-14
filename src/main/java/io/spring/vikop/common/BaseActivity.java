package io.spring.vikop.common;

import io.spring.vikop.user.User;
import io.spring.vikop.vote.Vote;
import io.spring.vikop.vote.VoteType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@MappedSuperclass
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseActivity implements Voteable, Comparable<BaseActivity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private Timestamp publishingDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private User user;
    private int votesCount;
    @Transient
    private VoteType userVote;

    @Override
    public void matchVote(List<Vote> votes) {
        Optional<Vote> vote = votes.stream()
                .filter(v -> v.getActivityId().equals(this.id)).findFirst();

        vote.ifPresent(v -> this.setUserVote(v.getVoteType()));
    }

    @Override
    public int compareTo(BaseActivity o) {
        return Comparator.comparing(BaseActivity::getPublishingDate)
                .thenComparing(BaseActivity::getId)
                .compare(this, o);
    }
}
