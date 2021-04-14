package io.spring.vikop.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.TreeSet;

@NoRepositoryBean
public interface MainActivityRepository<R> extends ActivityRepository<R> {

    Page<R> findAllByPublishingDateBetweenOrderByVotesCountDesc(Timestamp from, Timestamp to, Pageable pageable);

    List<R> findAllByUserId(@Param("id") Long id);

    TreeSet<R> findAllByTagNameList(@Param("tags") List<String> tagsNames);
}
