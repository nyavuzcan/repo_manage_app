package com.example.nk.serviceImpl;

import com.example.nk.entities.ConfirmationTokenEntity;
import com.example.nk.repository.ConfirmationTokenRepository;
import com.example.nk.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

  @Autowired
  ConfirmationTokenRepository confirmationTokenRepository;

  @Override
  public void saveConfirmationToken(ConfirmationTokenEntity confirmationToken) {
    confirmationTokenRepository.save(confirmationToken);
  }

  @Override
  public void deleteConfirmationToken(Long id) {
    confirmationTokenRepository.deleteById(id);
  }

  @Override
  public ConfirmationTokenEntity findConfirmationTokenByTokenEntity(String token) throws Exception {
    ConfirmationTokenEntity confirmationTokenEntity = confirmationTokenRepository.findByConfirmationToken(token);
      if (confirmationTokenEntity.getCreatedDate().before(new Date(System.currentTimeMillis()))) {
        confirmationTokenRepository.deleteById(confirmationTokenEntity.getId());
        throw new Exception("TOKEN EXPIRE");
      }
      return confirmationTokenEntity;
  }


}
