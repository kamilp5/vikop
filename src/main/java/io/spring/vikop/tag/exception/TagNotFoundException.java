package io.spring.vikop.tag.exception;

import io.spring.vikop.common.NotFoundException;

public class TagNotFoundException extends NotFoundException {

    public TagNotFoundException(String name){
        super("Tag with name: " + name + " not found");
    }
}
