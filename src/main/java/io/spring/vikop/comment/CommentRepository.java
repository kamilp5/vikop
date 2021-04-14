package io.spring.vikop.comment;

import io.spring.vikop.common.ActivityRepository;
import io.spring.vikop.common.CommentType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends ActivityRepository<Comment> {

    List<Comment> findAllByUser_IdAndCommentType(Long userId, CommentType commentType);
}
