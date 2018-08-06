package com.example.demo.controller;

import com.example.demo.model.JwtAuthenticationToken;
import com.example.demo.model.JwtUser;
import com.example.demo.repository.JwtUserRepository;
import com.example.demo.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private JwtUserRepository jwtUserRepository;

    private JwtGenerator jwtGenerator;
    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/user")
    public String createUser(@RequestBody JwtUser jwtUser)
    {
        jwtUserRepository.save(jwtUser);
        return "saved";
    }
    @PostMapping
    public  String generate(@RequestBody  JwtUser jwtUser)
    {

        if(checkIfUserExists(jwtUser.getUsername(),jwtUser.getPassword()))
        {

            String tokenn=jwtGenerator.generate(jwtUser);
            System.out.println("tokenn is "+tokenn);
            return  jwtGenerator.generate(jwtUser);
        }
        return "user does not exists";
    }

    public boolean checkIfUserExists(String username,String password)
    {
        if(jwtUserRepository.existsById(username))
        {
            Optional<JwtUser> user=jwtUserRepository.findById(username);
            JwtUser jwtUser=user.get();

            if(password.equals(jwtUser.getPassword()))
            {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}

