package com.example.nk.service;

import com.example.nk.entities.ConfirmationTokenEntity;

public interface ConfirmationTokenService {
  void saveConfirmationToken(ConfirmationTokenEntity confirmationToken);
  void deleteConfirmationToken(Long id);
  ConfirmationTokenEntity findConfirmationTokenByTokenEntity(String token) throws Exception;
}
