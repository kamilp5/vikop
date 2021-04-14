package io.spring.vikop.article.exception;

import io.spring.vikop.common.NotFoundException;

public class ArticleNotFoundException extends NotFoundException {

    public ArticleNotFoundException(long id) {
        super("Article with id: " + id + " not found.");
    }
}
