package io.spring.vikop.article;

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
public interface ArticleRepository extends MainActivityRepository<Article> {

    @Query(value = "SELECT * " +
            "FROM article WHERE title " +
            "LIKE %?1%",
            nativeQuery = true)
    List<Article> findByTitle(String title);

    Page<Article> findAllByPublishingDateBetweenOrderByVotesCountDesc(Timestamp from, Timestamp to, Pageable pageable);

    @Query(value = "SELECT * " +
            "FROM article " +
            "WHERE article.user_id = :id",
            nativeQuery = true)
    List<Article> findAllByUserId(@Param("id")Long id);

    @Query(value = "SELECT * " +
            "FROM article " +
            "INNER JOIN articles_tags ON article.id=articles_tags.article_id " +
            "INNER JOIN tag ON tag.id=articles_tags.tag_id " +
            "WHERE tag.name IN :tags",
            nativeQuery = true)
    TreeSet<Article> findAllByTagNameList(@Param("tags") List<String> tagsNames);
}
