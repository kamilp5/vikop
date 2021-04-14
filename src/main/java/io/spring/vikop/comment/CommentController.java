package io.spring.vikop.comment;

import io.spring.vikop.comment.dto.CommentDto;
import io.spring.vikop.comment.dto.NewCommentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentServiceAdapter serviceAdapter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addComment(@Valid @RequestBody NewCommentDto newComment) {
        return serviceAdapter.addComment(newComment);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @authorizationExpressions.isCommentOwner(#id, authentication)")
    public void deleteComment(@PathVariable Long id) {
        serviceAdapter.deleteComment(id);
    }
}
