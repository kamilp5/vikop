package io.spring.vikop.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.spring.vikop.comment.Comment;
import io.spring.vikop.common.MainActivity;
import io.spring.vikop.tag.Tag;
import io.spring.vikop.vote.Vote;
import lombok.*;
import org.hibernate.validator.constraints.URL;

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
public class Article extends MainActivity implements Serializable {

    @NotEmpty
    private String title;
    @Column(length = 999)
    @NotEmpty
    private String description;
    @NotEmpty
    @URL
    private String thumbnailUrl;
    @NotEmpty
    @URL
    private String articleUrl;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private List<Vote> votes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "articles_tags",
            joinColumns = {@JoinColumn(name = "article_id", referencedColumnName = "id")},
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
