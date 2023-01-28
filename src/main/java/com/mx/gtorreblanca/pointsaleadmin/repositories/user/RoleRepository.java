package com.mx.gtorreblanca.pointsaleadmin.repositories.user;

import com.mx.gtorreblanca.pointsaleadmin.entities.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
