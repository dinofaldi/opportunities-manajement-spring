package com.opportunities.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.opportunities.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
  @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = ?1")
  boolean existsByUsername(String username);

  Optional<User> findByUsername(String username);

  Optional<User> findFirstByToken(String token);
}
