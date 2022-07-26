package com.steady.steadyback.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByNameAndPhone(String name, String phone);
    public Boolean existsByEmail(String email);
    public Optional<User> findByEmail(String email);
}
