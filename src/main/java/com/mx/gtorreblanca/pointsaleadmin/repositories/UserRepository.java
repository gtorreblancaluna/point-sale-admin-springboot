package com.mx.gtorreblanca.pointsaleadmin.repositories;

import com.mx.gtorreblanca.pointsaleadmin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
