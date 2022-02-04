package com.ndiaye.mvcsession.repository;

import com.ndiaye.mvcsession.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
