package io.spring.vikop.comment.exception;

import io.spring.vikop.common.NotFoundException;

public class CommentNotFoundException extends NotFoundException {

    public CommentNotFoundException(long id) {
        super("Post comment with id: " + id + " not found.");
    }
}
