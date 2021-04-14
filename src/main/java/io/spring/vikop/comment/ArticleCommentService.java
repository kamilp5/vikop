package io.spring.vikop.comment;

import io.spring.vikop.article.Article;
import io.spring.vikop.article.ArticleService;
import io.spring.vikop.comment.dto.CommentMapper;
import io.spring.vikop.common.CommentType;
import io.spring.vikop.user.UserService;
import io.spring.vikop.vote.service.ArticleCommentVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCommentService extends CommentService<Article> {

    @Autowired
    public void setActivityService(ArticleService articleService) {
        super.setActivityService(articleService);
    }
    @Autowired
    public void setVoteService(ArticleCommentVoteService voteService) {
        super.setVoteService(voteService);
    }

    public ArticleCommentService(CommentRepository commentRepository,
                                 CommentMapper mapper,
                                 UserService userService) {
        super(commentRepository, userService, mapper);
    }

    public List<Comment> getUserComments(Long id) {
        return commentRepository.findAllByUser_IdAndCommentType(id, CommentType.ARTICLE_COMMENT);
    }
}
