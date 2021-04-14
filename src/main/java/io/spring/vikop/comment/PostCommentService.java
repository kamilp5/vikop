package io.spring.vikop.comment;

import io.spring.vikop.comment.dto.CommentMapper;
import io.spring.vikop.common.CommentType;
import io.spring.vikop.post.Post;
import io.spring.vikop.post.PostService;
import io.spring.vikop.user.UserService;
import io.spring.vikop.vote.service.PostCommentVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentService extends CommentService<Post> {

    @Autowired
    public void setActivityService(PostService postService) {
        super.setActivityService(postService);
    }

    @Autowired
    public void setVoteService(PostCommentVoteService voteService) {
        super.setVoteService(voteService);
    }

    public PostCommentService(CommentRepository commentRepository,
                              CommentMapper mapper,
                              UserService userService) {
        super(commentRepository, userService, mapper);
    }

    public List<Comment> getUserComments(Long id) {
        return commentRepository.findAllByUser_IdAndCommentType(id, CommentType.POST_COMMENT);
    }
}
