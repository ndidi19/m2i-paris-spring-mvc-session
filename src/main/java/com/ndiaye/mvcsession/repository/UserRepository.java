package com.ndiaye.mvcsession.repository;

import com.ndiaye.mvcsession.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
