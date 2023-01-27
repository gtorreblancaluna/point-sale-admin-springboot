package com.mx.gtorreblanca.pointsaleadmin.repositories;

import com.mx.gtorreblanca.pointsaleadmin.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
