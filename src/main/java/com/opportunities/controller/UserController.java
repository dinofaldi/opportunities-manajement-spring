package com.opportunities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opportunities.entity.User;
import com.opportunities.model.UserResponse;
import com.opportunities.model.WebResponse;
import com.opportunities.service.UserService;

@RestController
public class UserController {
  
  @Autowired
  private UserService userService;

  @GetMapping(
    path = "/api/users",
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<UserResponse> get(User user) {
    UserResponse userResponse = userService.get(user);
    return WebResponse.<UserResponse>builder().data(userResponse).build();
  }
}
