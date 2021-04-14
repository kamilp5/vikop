package io.spring.vikop.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.spring.vikop.article.Article;
import io.spring.vikop.comment.Comment;
import io.spring.vikop.post.Post;
import io.spring.vikop.tag.Tag;
import io.spring.vikop.vote.Vote;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Valid
    private UserDetails userDetails;
    @ManyToMany
    private List<UserRole> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_tags",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")}
    )
    private List<Tag> followedTags = new ArrayList<>();


    public void followTag(Tag tag) {
        this.followedTags.add(tag);
    }

    public void unfollowTag(Tag tag) {
        this.followedTags.remove(tag);
    }

    public List<String> getFollowedTagsAsListOfStrings() {
        List<String> tagsNames = new ArrayList<>();
        this.followedTags.forEach(t ->
                tagsNames.add(t.getName())
        );
        return tagsNames;
    }

    public String getRolesAsString() {
        StringBuilder rolesString = new StringBuilder();
        for (UserRole role : this.roles) {
            rolesString.append(role.getRole().getRoleName());
            rolesString.append(" ");
        }
        return rolesString.toString();
    }
}
