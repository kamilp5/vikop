package io.spring.vikop.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.spring.vikop.comment.Comment;
import io.spring.vikop.common.MainActivity;
import io.spring.vikop.tag.Tag;
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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post extends MainActivity implements Serializable {

    @Column(length = 9999)
    @NotEmpty
    private String content;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "post_id")
    private List<Vote> votes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "posts_tags",
            joinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")}
    )
    private List<Tag> tags = new ArrayList<>();

    @Override
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    @Override
    public void applyVote(Vote vote) {
        this.votes.add(vote);
        setVotesCount(this.getVotesCount() + vote.scoreToApply());
    }
}
