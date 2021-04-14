package io.spring.vikop.common;

import io.spring.vikop.comment.Comment;

import java.util.List;


public abstract class MainActivity extends BaseActivity {

    public abstract void addComment(Comment comment);

    public abstract List<Comment> getComments();

    public abstract void setComments(List<Comment> comments);
}
