package io.spring.vikop.post.exception;

import io.spring.vikop.common.NotFoundException;

public class PostNotFoundException extends NotFoundException {

    public PostNotFoundException(long id) {
        super("Post with id: " + id + " not found.");
    }
}
