package com.example.nk.serviceImpl;

import com.example.nk.dto.UserRequest;
import com.example.nk.entities.ConfirmationTokenEntity;
import com.example.nk.entities.UserEntity;
import com.example.nk.repository.UserRepository;
import com.example.nk.service.ConfirmationTokenService;
import com.example.nk.service.EmailSenderService;
import com.example.nk.service.UserService;
import com.example.nk.util.UserUtil;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  UserService userService;
  @Autowired
  ConfirmationTokenService confirmationTokenService;
  @Autowired
  EmailSenderService emailSenderService;
  @Autowired
  Mapper mapper;
  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  UserUtil userUtil;
  @Override
  public UserEntity findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public void saveNewUser(UserRequest userRequest) throws Exception {


    if (userUtil.isEmailUsing(userRequest.getEmail())){
       UserEntity userEntity = userRepository.findByEmail(userRequest.getEmail());
      if (!userEntity.isEnabled()) {
        final ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(userEntity);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        userService.sendConfirmationMail(userEntity.getEmail(), confirmationToken.getConfirmationToken());
        throw new Exception("RE-SEND CONFIRM EMAIL");
      }
      throw new Exception("THIS EMAIL IS ALREADY TAKEN");
    }

    if (userUtil.isUsernameUsing(userRequest.getUsername())) throw new Exception("THIS USERNAME IS ALREADY TAKEN");

    final String encryptedPassword = bCryptPasswordEncoder.encode(userRequest.getPassword());
    userRequest.setPassword(encryptedPassword);
    UserEntity userEntity = mapper.map(userRequest,UserEntity.class);
    final UserEntity createdUser = userRepository.save(userEntity);

    final ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(userEntity);
    confirmationTokenService.saveConfirmationToken(confirmationToken);
    userService.sendConfirmationMail(userEntity.getEmail(), confirmationToken.getConfirmationToken());
  }

  @Override
  public void confirmUser(ConfirmationTokenEntity confirmationToken) {
    final UserEntity userEntity = confirmationToken.getUser();
    userEntity.setEnabled(true);
    userRepository.save(userEntity);
    confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());

  }

  @Override
  public void sendConfirmationMail(String userMail, String token) {

    final SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(userMail);
    mailMessage.setSubject("Mail Confirmation Link!");
    mailMessage.setFrom("<MAIL>");
    mailMessage.setText(
        "Thank you for registering. Please click on the below link to activate your account. " + "http://localhost:8090/authenticate/confirm?token="
            + token);

    emailSenderService.sendEmail(mailMessage);
  }
}
