package com.example.nk.util;

import com.example.nk.entities.UserEntity;
import com.example.nk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class UserUtil {

  @Autowired
  UserRepository userRepository;

  public boolean isEmailUsing(String email){
    UserEntity userEntity = userRepository.findByEmail(email);
    if (Objects.nonNull(userEntity)) return true;
    else return false;
  }


  public boolean isUsernameUsing(String username){
    UserEntity userEntity = userRepository.findByUsername(username);
    if (Objects.nonNull(userEntity)) return true;
    else return false;
  }



}
