package com.example.nk.repository;

import com.example.nk.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
  UserEntity findByEmail(String email);
  UserEntity findByUsername(String username);
}
