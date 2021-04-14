package io.spring.vikop.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ActivityRepository<R> extends JpaRepository<R, Long> {
}
