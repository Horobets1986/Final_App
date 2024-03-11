package com.horobets.repository;

import com.horobets.models.ERole;
import com.horobets.models.Role;
import com.horobets.models.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);

}
