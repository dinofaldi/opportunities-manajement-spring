package com.opportunities.service;

import org.springframework.stereotype.Service;

import com.opportunities.entity.User;
import com.opportunities.model.UserResponse;
@Service
public class UserService {
  public UserResponse get(User user) {
    return UserResponse.builder()
      .username(user.getUsername())
      .name(user.getName())
      .build();
  }
}
