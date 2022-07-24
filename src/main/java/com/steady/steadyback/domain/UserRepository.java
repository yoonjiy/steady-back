package com.steady.steadyback.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByNameAndPhone(String name, String phone);
    User findByEmail(String email );
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
