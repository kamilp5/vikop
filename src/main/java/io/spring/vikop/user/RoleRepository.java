package io.spring.vikop.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRole,Long> {

    UserRole findByRole(RoleType role);
}
