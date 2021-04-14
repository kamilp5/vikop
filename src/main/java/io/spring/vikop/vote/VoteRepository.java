package io.spring.vikop.vote;

import io.spring.vikop.common.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByUser_IdAndActivityIdAndActivityType(Long userId, Long activityId, ActivityType activityType);

    List<Vote> findAllByUser_IdAndActivityType(Long userId, ActivityType activityType);

    List<Vote> findAllByUser_IdAndActivityIdInAndActivityType(Long userId, List<Long> activityIds, ActivityType activityType);
}
