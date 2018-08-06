package com.example.demo.controller;

import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.Users;
import com.example.demo.security.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

   private JWTAuthenticationFilter jwtAuthenticationFilter;
  @PostMapping("/signup")
    public String signUp(@RequestBody Users users)
  {
      userRepository.save(users);

      return "saved";
  }
  @PostMapping("/login")
    public String login(@RequestBody Users users)
  {

      return jwtAuthenticationFilter.successfullAuthentication(users);
  }
}
