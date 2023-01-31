package com.mx.gtorreblanca.pointsaleadmin.repositories.user;

import com.mx.gtorreblanca.pointsaleadmin.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndEnabledTrue(String email);
    Optional<User> findByUsernameOrEmailAndEnabledTrue(String username, String email);
    Optional<User> findByUsernameAndEnabledTrue(String username);
    Boolean existsByUsernameAndEnabledTrue(String username);
    Boolean existsByEmailAndEnabledTrue(String email);
    Set<User> findByEnabledTrue();
}
