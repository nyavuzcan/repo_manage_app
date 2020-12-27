package com.example.nk.controller;

import com.example.nk.dto.AuthenticationRequest;
import com.example.nk.dto.AuthenticationResponse;
import com.example.nk.dto.UserRequest;
import com.example.nk.entities.ConfirmationTokenEntity;
import com.example.nk.entities.UserEntity;
import com.example.nk.service.ConfirmationTokenService;
import com.example.nk.service.MyUserDetailsService;
import com.example.nk.service.UserService;
import com.example.nk.util.JwtUtil;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private ConfirmationTokenService confirmationTokenService;
  @Autowired
  private MyUserDetailsService myUserDetailsService;
  @Autowired
  private JwtUtil jwtTokenUtil;
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/verify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws  Exception
  {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword())
      );

    } catch (BadCredentialsException e){
      throw new Exception("Incorrect username or password", e);
    }
    final UserDetails userDetails = myUserDetailsService
        .loadUserByUsername(authenticationRequest.getUserName());

    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public String signUp(@RequestBody  UserRequest userRequest) throws Exception {

    userService.saveNewUser(userRequest);

    return "redirect:/sign-in";
  }


  @GetMapping("/confirm")
  String confirmMail(@RequestParam("token") String token) throws Exception {

    Optional<ConfirmationTokenEntity> optionalConfirmationToken = Optional.ofNullable(confirmationTokenService.findConfirmationTokenByTokenEntity(token));

    optionalConfirmationToken.ifPresent(userService::confirmUser);

    return "/sign-in";
  }


}
