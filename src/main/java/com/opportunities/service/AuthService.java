package com.opportunities.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.opportunities.entity.User;
import com.opportunities.model.LoginUserRequest;
import com.opportunities.model.RegisterUserRequest;
import com.opportunities.model.TokenResponse;
import com.opportunities.repository.UserRepository;
import com.opportunities.security.BCrypt;

import java.util.UUID;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ValidationService validationService;

  @Transactional
  public void register(RegisterUserRequest request) {
    validationService.validate(request);

    if(userRepository.existsByUsername(request.getUsername())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
    }

    User user = new User();
    user.setId(UUID.randomUUID().toString());
    user.setUsername(request.getUsername());
    user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt())); 
    user.setName(request.getName());

    userRepository.save(user);
  }

  @Transactional
  public TokenResponse login(LoginUserRequest request) {
    validationService.validate(request);

    User user = userRepository.findByUsername(request.getUsername())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong"));

    if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
      user.setToken(UUID.randomUUID().toString());
      user.setTokenExpiredAt(next30Days());
      userRepository.save(user);

      return TokenResponse.builder()
        .token(user.getToken())
        .expiredAt(user.getTokenExpiredAt())
        .build();
    } else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
    }
  }

  private Long next30Days() {
      return System.currentTimeMillis() + (1000 * 60 * 24 * 30);
  }

  @Transactional
  public void logout(User user) {
    user.setToken(null);
    user.setTokenExpiredAt(null);

    userRepository.save(user);
  }
}