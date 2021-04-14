package io.spring.vikop.comment;

import io.spring.vikop.comment.dto.CommentDto;
import io.spring.vikop.comment.dto.NewCommentDto;
import io.spring.vikop.common.CommentType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentServiceAdapter {

    private ArticleCommentService articleCommentService;
    private PostCommentService postCommentService;

    public CommentDto addComment(NewCommentDto newCommentDto) {
        if (newCommentDto.getCommentType().equals(CommentType.ARTICLE_COMMENT)) {
            return articleCommentService.addComment(newCommentDto);
        } else {
            return postCommentService.addComment(newCommentDto);
        }
    }

    public void deleteComment(Long id) {
        articleCommentService.delete(id);
    }
}
