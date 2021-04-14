package io.spring.vikop.common;

import java.util.Collection;
import java.util.function.Supplier;

public interface ActivityService<R> {

    Supplier<NotFoundException> activityNotFound(Long id);

    void save(R r);

    void delete(Long id);

    R getById(Long id);

    void findAndMatchUserVotes(Collection<? extends BaseActivity> activities);
}
