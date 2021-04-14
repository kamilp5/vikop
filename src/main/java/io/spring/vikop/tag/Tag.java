package io.spring.vikop.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.spring.vikop.article.Article;
import io.spring.vikop.post.Post;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Tag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int followCount;
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Article> articles = new ArrayList<>();
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    void increaseFollowCount(){
        this.followCount++;
    }
    void decreaseFollowCount(){
        this.followCount--;
    }
}
