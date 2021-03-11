package com.woowang.myboard.repository;

import com.woowang.myboard.model.ERole;
import com.woowang.myboard.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
