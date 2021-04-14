package io.spring.vikop.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    User getByUsername(String username);


    @Query("SELECT u FROM User u WHERE u.userDetails.email=?1")
    Optional<User> findByEmail(String email);
}
