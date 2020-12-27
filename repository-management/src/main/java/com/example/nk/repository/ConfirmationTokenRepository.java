package com.example.nk.repository;

import com.example.nk.entities.ConfirmationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity,Long> {
  ConfirmationTokenEntity findByConfirmationToken(String token);
}
