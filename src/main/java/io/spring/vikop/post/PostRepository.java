package io.spring.vikop.post;

import io.spring.vikop.common.MainActivityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.TreeSet;

@Repository
public interface PostRepository extends MainActivityRepository<Post> {


    Page<Post> findAllByPublishingDateBetweenOrderByVotesCountDesc(Timestamp from, Timestamp to, Pageable pageable);

    @Query(value = "SELECT * " +
            "FROM post " +
            "WHERE post.user_id = :id ",
            nativeQuery = true)
    List<Post> findAllByUserId(@Param("id") Long id);

    @Query(value = "SELECT * " +
            "FROM post " +
            "INNER JOIN posts_tags ON post.id=posts_tags.post_id " +
            "INNER JOIN tag ON tag.id=posts_tags.tag_id " +
            "WHERE tag.name IN :tags",
            nativeQuery = true)
    TreeSet<Post> findAllByTagNameList(@Param("tags") List<String> tagsNames);
}
