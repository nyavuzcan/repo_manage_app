package com.example.nk.serviceImpl;

import com.example.nk.entities.UserEntity;
import com.example.nk.repository.UserRepository;
import com.example.nk.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByUsername(userName);
    if (userEntity!= null){
      if (!userEntity.isEnabled()){
        throw new UsernameNotFoundException("USER NOT CONFIRM");
      }
      return new User(userEntity.getUsername(),userEntity.getPassword(),new ArrayList<>());
    }
  else throw new UsernameNotFoundException("User not found");
  }

/**
 *  List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
 *                 return buildUserForAuthentication(user, authorities);
 *
 */

/*  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByEmail(email);
    if (Objects.isNull(userEntity)) return null;

    return new org.springframework.security.core.userdetails.User(
        userEntity.getEmail(),
        userEntity.getPassword(),
        getAuthorities(userEntity));

  }
  private Set<GrantedAuthority> getAuthorities(UserEntity user) {
    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("user");
    authorities.add(grantedAuthority);

    return authorities;

  }*/
}
