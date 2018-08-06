package com.example.polls.controller;

import com.example.polls.model.Tasks;
import com.example.polls.model.User;
import com.example.polls.payload.JwtAuthenticationResponse;
import com.example.polls.repository.JwtauthenticationResponseRepository;
import com.example.polls.repository.TaskRepository;
import com.example.polls.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtauthenticationResponseRepository jwtauthenticationResponseRepository;

    @PostMapping("/save")
    public String assignTask(@RequestBody User user,@RequestHeader HttpHeaders header) {
          String header1=header.getFirst("authorization");
          String token=header1.substring(7,header1.length());
             Long subject=getUserIdFromJWT(token);
             System.out.println("subject is"+subject);
             if(userRepository.existsById(subject))
             {
                Optional<User> userOptional= userRepository.findById(subject);
                User user1=userOptional.get();
                user1.setTasks(user.getTasks());
                userRepository.save(user1);
             }

//          if(jwtauthenticationResponseRepository.existsByAccessToken(token))
//          {
//              System.out.println("token is"+token);
//            Optional<JwtAuthenticationResponse> jwt= jwtauthenticationResponseRepository.findByAccessToken(token);
//           JwtAuthenticationResponse jwtAuthenticationResponse=jwt.get();
//           User user1=jwtAuthenticationResponse.getUser();
//              System.out.println("id1 is"+user1.getId());
//          if(userRepository.existsById( user1.getId())) {
//               System.out.println("id2 is"+user1.getId());
//              Optional<User> user2=userRepository.findById(user1.getId());
//              User user3=user2.get();
//              user3.setTasks(user.getTasks());
//               userRepository.save(user3);
//
////            Optional<User> uploadOptional = userRepository.findById(id);
////            User user1 = uploadOptional.get();
////            user1.setTasks(user.getTasks());
////            return userRepository.save(user1);
//
//          }

//            return "done";
////
//        }
        return null;

    }
    @DeleteMapping("delete/{id}")
    public User deleteTask(@PathVariable long id)
    {
        if(taskRepository.existsById(id))
        {
            taskRepository.deleteById(id);
        }
        return null;
    }

    @GetMapping("gettask/{id}")
    public User getTask(@PathVariable long id)
    {
        if(taskRepository.existsById(id))
        {
//            return taskRepository.getOne(id);
        }
        return null;
    }
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

}
