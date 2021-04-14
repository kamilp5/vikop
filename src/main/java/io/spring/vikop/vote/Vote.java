package io.spring.vikop.vote;

import io.spring.vikop.user.User;
import io.spring.vikop.common.ActivityType;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private User user;
    @Enumerated(EnumType.STRING)
    private VoteType voteType;
    private Long activityId;
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;
    @Transient
    private int scoreToApply = 0;

    public Vote() {
    }

    public Vote(Long activityId, ActivityType activityType) {
        this.activityId = activityId;
        this.activityType = activityType;
    }

    public void setUp(VoteType voteType) {
        if (this.voteType == null) {
            this.scoreToApply = voteType.getValue();
        } else {
            this.scoreToApply = -this.voteType.getValue() + voteType.getValue();
        }
        this.voteType = voteType;
    }

    public int scoreToApply() {
        return scoreToApply;
    }
}
