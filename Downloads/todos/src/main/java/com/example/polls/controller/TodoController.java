package com.example.polls.controller;

import com.example.polls.model.Tasks;
import com.example.polls.model.User;
import com.example.polls.repository.TaskRepository;
import com.example.polls.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
public class TodoController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/todos")
    public String assignTask(@RequestBody User user, @RequestHeader HttpHeaders header) {
        String header1 = header.getFirst("authorization");
        String token = header1.substring(7, header1.length());
        Long subject = getUserIdFromJWT(token);
        System.out.println("subject is" + subject);
        if (userRepository.existsById(subject)) {
            Optional<User> userOptional = userRepository.findById(subject);
            User user1 = userOptional.get();
            user1.setTasks(user.getTasks());
            userRepository.save(user1);
            return "saved successfully";
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return "deleted";
        }
        return "could not delete";
    }

    @GetMapping("todos/{id}")
    public @ResponseBody
    Tasks getTask(@PathVariable long id) {
        if (taskRepository.existsById(id)) {
            Optional<Tasks> s = taskRepository.findById(id);
            Tasks tasks = s.get();
            tasks.getId();
            tasks.getDescription();
            tasks.isCompleted();
            return tasks;
        }
        return null;
    }

    @GetMapping("/todos")
    public @ResponseBody Iterable<Tasks> getAll() {
        return taskRepository.findAll();
    }

    @PutMapping("/todos/{id}")
    public @ResponseBody Tasks update(@PathVariable long id,@RequestBody Tasks tasks)
    {

        System.out.println("request is"+tasks.getDescription());
        if(taskRepository.existsById(id))
        {
            System.out.println("id is"+id);
            Optional<Tasks> tasks1=taskRepository.findById(id);
            Tasks tasks2=tasks1.get();
            tasks2.setCompleted(tasks.isCompleted());
            tasks2.setDescription(tasks.getDescription());
            return tasks2;
        }
        return null;
    }

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    private Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return Long.parseLong(claims.getSubject());
    }

}
