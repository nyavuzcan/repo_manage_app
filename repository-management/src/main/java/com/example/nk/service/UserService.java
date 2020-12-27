package com.example.nk.service;

import com.example.nk.dto.UserRequest;
import com.example.nk.entities.ConfirmationTokenEntity;
import com.example.nk.entities.UserEntity;

public interface UserService {
  UserEntity findUserByEmail(String email);

  void saveNewUser(UserRequest userRequest) throws Exception;

  void confirmUser(ConfirmationTokenEntity confirmationToken);

  void sendConfirmationMail(String userMail, String token);
}